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

import com.centris.campus.daoImpl.ClassFeeSetupDaoImpl;
import com.centris.campus.daoImpl.ElectionDaoImpl;
import com.centris.campus.daoImpl.FeeCollectionDaoImpl;
import com.centris.campus.delegate.ClassBD;
import com.centris.campus.delegate.ClassFeeSetupBD;
import com.centris.campus.delegate.FeeCollectionBD;
import com.centris.campus.delegate.FeeMasterDelegate;
import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.TermMasterDelegate;
import com.centris.campus.forms.ClassFeeSetupForm;
import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddFeeVO;
import com.centris.campus.vo.ClassFeeSetupVo;
import com.centris.campus.vo.FeeCollectionVo;
import com.centris.campus.vo.FeeNameVo;
import com.centris.campus.vo.ParentVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentConcessionVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.TermMasterVo;

public class FeeCollectionAction extends DispatchAction {

	private static Logger logger = Logger.getLogger(FeeCollectionAction.class);
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");

	public synchronized ActionForward getFeeCollectionDetails(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in FeeCollectionAction : getFeeCollectionDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_FEE);

			String FeeCodeDetails = request.getParameter("student");

			FeeCollectionVo collectionVo = new FeeCollectionBD().getFeeCollectionAmount(FeeCodeDetails);

			request.setAttribute("FeeCollectionVo", collectionVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in FeeCollectionAction : getFeeCollectionDetails Ending");

		return mapping.findForward(MessageConstants.FEE_COLLECTION_ENTRY);
	}

	public synchronized ActionForward saveFeeCollection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : saveFeeCollection Starting");
		try {

			System.out.println("in saveFeeCollection ");

			String addmissionNo = request.getParameter("addmissionNo");
			String accYear = request.getParameter("accodemicyear");
			String termId = request.getParameter("termid");
			String classId = request.getParameter("classd");
			String feeId = request.getParameter("feeIdArray");
			String feeAmountArray = request.getParameter("feeAmountArray");
			String totalAmount=request.getParameter("totalAmount");
			String feeSetting=request.getParameter("feeSetting");
			String fineAmount=request.getParameter("fineAmount");
			String payingAmount=request.getParameter("payingAmount");
			String advanceCarry=request.getParameter("advanceCarry");
			String duesCarry=request.getParameter("duesCarry");
			String paymentParticulars=request.getParameter("paymentParticulars");
			String paymentMode=request.getParameter("paymentMode");
			String dd_cheque_bank=request.getParameter("dd_cheque_bank");
			String dd_cheque_date=request.getParameter("dd_cheque_date");
			String concessionAmount=request.getParameter("concessionAmount");
			ArrayList<FeeNameVo> feeList = new ArrayList<FeeNameVo>();

			for (int i = 0; i < feeId.split(",").length; i++) {

				FeeNameVo feevo = new FeeNameVo();
				feevo.setFeecode(feeId.split(",")[i]);
				feevo.setFeeAmountArray(Double.parseDouble(feeAmountArray.split(",")[i]));
				feeList.add(feevo);
			}

			FeeCollectionVo collectionVo = new FeeCollectionVo();
			collectionVo.setFineAmount(Double.parseDouble(fineAmount));
			collectionVo.setAddmissionno(addmissionNo);
			collectionVo.setAccYear(accYear);
			collectionVo.setTermid(termId);
			collectionVo.setClassId(classId);
			collectionVo.setFeeNamelist(feeList);
			collectionVo.setUserID(HelperClass.getCurrentUserID(request));
			collectionVo.setTot_actual_amt(Double.parseDouble(totalAmount));
			collectionVo.setCurrent_payment(Double.parseDouble(payingAmount));
			collectionVo.setAdvanceCarry(Double.parseDouble(advanceCarry));
			collectionVo.setDuesCarry(Double.parseDouble(duesCarry));
			collectionVo.setPaymentMode(paymentMode);
			collectionVo.setDd_cheque_bank(dd_cheque_bank);
			collectionVo.setDd_cheque_date(dd_cheque_date);
			collectionVo.setPaymentPatriculars(paymentParticulars);
			collectionVo.setConcession(Double.parseDouble(concessionAmount));
			collectionVo.setFeeSettingList(feeSetting);
			
			

			String status = new FeeCollectionBD().saveFeeCollection(collectionVo);
			System.out.println("status"+status);
			
			
			

			JSONObject object = new JSONObject();
			object.put("status", status);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : saveFeeCollection Ending");

		return null;
	}

	public ActionForward getSectionByClass(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getSectionByClass Starting");
		try {

			String classId = request.getParameter("classId");
			String location=request.getParameter("location");
			ArrayList<ReportMenuVo> classesList = new ReportsMenuBD()
					.getSectionsByClass(classId,location);
					

			JSONObject object = new JSONObject();
			object.put("SectionList", classesList);

			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getSectionByClass Ending");

		return null;
	}

	public ActionForward downloadfeecollectionXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : downloadfeecollectionXLS  Starting");

		try {

			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request.getRealPath("Reports/feecollectiondetailsxls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager.compileReport(design);
			String currentYear = request.getSession(false).getAttribute("current_academicYear").toString();
			String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
			/*ArrayList<FeeCollectionVo> feeCollectionList = null;
			
				feeCollectionList = new FeeCollectionBD()
						.getFeeCollectionDetails(currentYear);*/


			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);

			request.setAttribute("ClassList", new ClassBD().getClassDetails(schoolLocation));
			
			ArrayList<FeeNameVo> feeCollectionList = null;

			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
			String termId = request.getParameter("termId");

			String stuId=request.getParameter("stuId");
			
			if(stuId == null){
				stuId="%%";
				System.out.println(stuId);
			}
			

			String studId=request.getParameter("");

			TermMasterVo vo = new TermMasterVo();
			
			ArrayList<TermMasterVo> termlist = new TermMasterDelegate().termList(vo);
			
			if (classId != null) {

				feeCollectionList = new FeeCollectionBD()
						.getSearchFeeCollectionDetails(currentYear, classId,
				sectionId,termId,stuId);

			} else {

				feeCollectionList = new FeeCollectionBD()
						.getFeeCollectionDetails(currentYear);
			}

			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					feeCollectionList);
			Map parameters = new HashMap();

			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/Feecollectiondetailsxls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Fee Collection Details Excel Report" };
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

			pdfxls = new File(
					request.getRealPath("Reports/Feecollectiondetailsxls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=Feecollectiondetailsxls.xls");
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
				+ " Control in FeeCollectionAction : downloadfeecollectionXLS   Ending");
		return null;

	}

	public ActionForward downloadfeecollectionPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : downloadfeecollectionPDF  Starting");

		try {

			System.out.println("downloading pdf");

			String currentYear = request.getSession(false).getAttribute("current_academicYear").toString();
			String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();


			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_FEE);

			request.setAttribute("ClassList", new ClassBD().getClassDetails(schoolLocation));
			

			ArrayList<FeeNameVo> feeCollectionList = null;

			String classId = request.getParameter("classId");
			String sectionId = request.getParameter("sectionId");
			String termId = request.getParameter("termId");
			String studId=request.getParameter("");
			System.out.println("termId:::"+termId);
			System.out.println("sectionId:::"+sectionId);

			System.out.println("classId:::"+classId);

			String stuId=request.getParameter("stuId");
			
			if(stuId == null){
				stuId="%%";
				System.out.println(stuId);
			}
			
			TermMasterVo vo = new TermMasterVo();
			
			ArrayList<TermMasterVo> termlist = new TermMasterDelegate().termList(vo);
			
			if (classId != null) {

				feeCollectionList = new FeeCollectionBD()
						.getSearchFeeCollectionDetails(currentYear, classId,

								sectionId,termId,stuId);

			} else {

				feeCollectionList = new FeeCollectionBD()
						.getFeeCollectionDetails(currentYear);
			}

		

			String sourceFileName = request
					.getRealPath("Reports/feecollectionDetailsPDF.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);

			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					feeCollectionList);

			String PropfilePath = getServlet().getServletContext().getRealPath(
					"")
					+ "\\images\\" + ImageName.trim();

			String schName = res.getString("SchoolName");
			String schAddLine1 = res.getString("AddressLine1");

			Map parameters = new HashMap();

			parameters.put("Image", PropfilePath);

		

			byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
					parameters, beanColDataSource);

			response.setContentType("application/pdf");

			response.setContentLength(bytes.length);

			response.setHeader("Content-Disposition", "outline; filename=\""
					+ "FeeCollectionDetailsPDF - " + ".pdf\"");

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
				+ " Control in FeeCollectionAction : downloadfeecollectionPDF  Ending");
		return null;

	}

	public ActionForward getAllStudentNames(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getAllStudentNames  Starting");
		
		
		try {
			
			
			String sectionid = request.getParameter("sectionid").trim();
			String accyear = request.getParameter("accyear").trim();
			String classname = request.getParameter("classname").trim();

			
			
			List<ParentVO> studentlist = new FeeCollectionBD().getAllStudentNamesReportBD(sectionid,classname,accyear);
			
			JSONObject object=new JSONObject();
			 
			 object.put("studentlist", studentlist);
			 
			 response.getWriter().print(object);
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getAllStudentNames  Ending");
		
		return null;
	}
	
	public ActionForward getFeeCollectionAndSetup(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getFeeCollectionAndSetup  Starting");
		
	try {
		
		
		
		ArrayList<ClassFeeSetupVo> allfeesdetails = null;
		
		/*ArrayList<ClassFeeSetupVo> studentval = null;*/
	
		ClassFeeSetupForm clsform =(ClassFeeSetupForm)form; 
		
		ClassFeeSetupPojo feeSetupPojo = new ClassFeeSetupPojo();
		
		
		feeSetupPojo.setAccYear(clsform.getAccyear());
		feeSetupPojo.setClassId(clsform.getClassname());
		feeSetupPojo.setStudentId(clsform.getStudentname());
		
		System.out.println(clsform.getAccyear()+"    "+clsform.getClassname()+"    "+clsform.getStudentname());
		
		allfeesdetails = FeeCollectionBD.getAllFees(feeSetupPojo);
		ClassFeeSetupVo studentval = FeeCollectionBD.getStudentValBd(feeSetupPojo);
		
		
		
		
		request.setAttribute("getActiveFeeMasterSetupDetails",allfeesdetails);
		request.setAttribute("studentval",studentval);
		request.setAttribute("fmscode",allfeesdetails.get(0).getFeesettingcode());
		request.setAttribute("feeamt",allfeesdetails.get(0).getTotalfee());
		
		String fee=allfeesdetails.get(0).getTotalfee();
		Double feem = Double.parseDouble(fee)*12; 
		request.setAttribute("totalfeeamt",feem);
		ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
		request.setAttribute("AccYearList", accYearList);
		
		
		String currentaccyear=clsform.getAccyear();
		
		request.setAttribute("currentaccyear", currentaccyear);

		

		ArrayList<ClassFeeSetupVo> classSetupList = new ArrayList<ClassFeeSetupVo>();
			classSetupList = new ClassFeeSetupBD().getFeeSetupDetails(currentaccyear);
		request.setAttribute("classSetupList", classSetupList);
		

	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getFeeCollectionAndSetup  Ending");
		
		return mapping.findForward(MessageConstants. getFeeCollection);
	}
	
	public ActionForward getmodeofpaymentAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getmodeofpaymentAction  Starting");
	
	
		try {
			
			String studentid = request.getParameter("studentid");
			String admissionnum = request.getParameter("admissionnum");
			String classid = request.getParameter("classid");
			String hfmscode = request.getParameter("hfmscode");
			
			ClassFeeSetupPojo feeSetupPojo = new ClassFeeSetupPojo();
			
			
			feeSetupPojo.setStudentId(studentid);
			feeSetupPojo.setAddmissionNo(admissionnum);
			feeSetupPojo.setClassId(classid);
			feeSetupPojo.setFeesettingCode(hfmscode);
			
			ArrayList<ClassFeeSetupVo> paymentdetailstype =  FeeCollectionBD.getPaymentTypeBD(feeSetupPojo);
			
			JSONObject obj = new JSONObject();
			obj.put("feeList",paymentdetailstype);
			response.getWriter().print(obj);
		
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getmodeofpaymentAction  Ending");
		
		return null;
	}
	
	public ActionForward insertfeecollectiondetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getmodeofpaymentAction  Starting");
	

		try {
			
			
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);

			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);
			
			
			
			
			ArrayList<ReportMenuVo> accYearList=new ReportsMenuBD().getAccYears();
			
			request.setAttribute("AccYearList", accYearList);
			
			
			List<ReportMenuVo> classList=new FeeMasterDelegate().getclasslist();
			
			request.setAttribute("classlist", classList);
			
			ClassFeeSetupForm form1 = (ClassFeeSetupForm) form;
			
			String createdUser = HelperClass.getCurrentUserID(request);
			
			

			form1.setCurrentuserid(createdUser);
			String accyear=form1.getAccyear();
			String fmsname=form1.getHfmsname();
			String studentid=form1.getStudentid();
			String admissionNum=form1.getAdmissionNum();
			String payment_mode=form1.getPayment_mode();
			String paymentType=form1.getPaymentType();
			double payingamount=form1.getPayingamount();
			double totalfeeamt=	form1.getTotalfeeamt();
			double dueamount=	form1.getDueamount();
			String payment_date_id=form1.getPayment_date_id();
			String hclassname=form1.getHclassname();
			String paymentMonth=form1.getPaymentMonth();
			String monthlist=form1.getMonthlist();
			String cheque_no=form1.getCheque_no();
			
			

			boolean inserfeecollection =FeeCollectionBD.inserfeecollection(form1);
			
			if(inserfeecollection==true)
			{
				
				request.setAttribute("successMessage", MessageConstants.SuccessUpMsg);
				
			}

			else
			{
				request.setAttribute("errorMessage", MessageConstants.ErrorUpMsg);

			}
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getmodeofpaymentAction  Ending");
		
		return mapping.findForward(MessageConstants.setupAndCollection);
	}
	
	public ActionForward validateFeeCount(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getFeeCollectionAndSetup  Starting");
		
	try {
		
		String currentaccyear = request.getParameter("accYear");
		String sec = request.getParameter("");
		String classId = request.getParameter("classname");
		
		int feeCount = new ClassFeeSetupDaoImpl().validateFeeCount(currentaccyear,sec,classId);
	
		
		
		JSONObject obj = new JSONObject();
		obj.put("feeCount", feeCount);
		response.getWriter().print(obj);
		
		
	   } 
	
	catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getFeeCollectionAndSetup  Ending");
		
		return null;
	}
	public synchronized ActionForward editFeeCollectionDetails(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getFeeCollectionDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_FEE);

			String FeeCodeDetails = request.getParameter("FeeCode");

			FeeCollectionVo collectionVo = new FeeCollectionBD()
					.getFeeCollectionAmount(FeeCodeDetails);

			request.setAttribute("FeeCollectionVo", collectionVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getFeeCollectionDetails Ending");

		return mapping.findForward(MessageConstants.FEE_COLLECTION_EDIT);
	}
	
	public synchronized ActionForward feeCollectionStudentWise(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in FeeCollectionAction : feeCollectionStudentWise Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_FEECOLLECTION);
			
			String FeeCodeDetails = request.getParameter("student")+","+request.getParameter("academicYear")+","+request.getParameter("loc_id");

			FeeCollectionVo collectionVo = new FeeCollectionBD().getFeeCollectionAmount(FeeCodeDetails);
			
			request.setAttribute("FeeCollectionVo", collectionVo);
			request.setAttribute("schoolName", HelperClass.getSchoolName(request.getParameter("loc_id")));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in FeeCollectionAction : feeCollectionStudentWise Ending");

		return mapping.findForward(MessageConstants.FEE_COLLECTION_STUDENT_WISE);
	}
	public synchronized ActionForward feeCollectionDetails(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in FeeCollectionAction : feeCollectionDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_FEE);

			String academic_year = request.getParameter("accYear");
			String classId=request.getParameter("classId");
			String termId=request.getParameter("term");
			String specialization=request.getParameter("specialization");
			String FeeCodeDetails = request.getParameter("student")+","+academic_year+","+classId+","+termId+","+specialization;

			ArrayList<FeeNameVo> FeeCollectionDetails = new FeeCollectionBD().getFeeCollectionDetails(FeeCodeDetails);
			System.out.println("FeeCollectionDetails"+FeeCollectionDetails);
			JSONObject obj=new JSONObject();
			obj.put("FeeCollectionDetails", FeeCollectionDetails);
			response.getWriter().print(obj);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in FeeCollectionAction : feeCollectionDetails Ending");

		return null;
	}
	
	public synchronized ActionForward feePaidDetails(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in FeeCollectionAction : feePaidDetails Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_FEE);

			String academic_year = request.getParameter("accYear");
			String classId=request.getParameter("classId");
			String termId=request.getParameter("term");
			String specialization=request.getParameter("specialization");
			String FeeCodeDetails = request.getParameter("student")+","+academic_year+","+classId+","+termId+","+specialization;

			ArrayList<FeeNameVo> FeeCollectionDetails = new FeeCollectionBD().getFeePaidDetails(FeeCodeDetails);
			
			JSONObject obj=new JSONObject();
			obj.put("FeeCollectionDetails", FeeCollectionDetails);
			response.getWriter().print(obj);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in FeeCollectionAction : feePaidDetails Ending");

		return null;
	}
	
	public synchronized ActionForward TransportfeeCollectionStudentWise(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in FeeCollectionAction : TransportfeeCollectionStudentWise Starting");
		try {
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_TRANSPORTFEE);

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);

			String academic_year = request.getParameter("accyId");
			String loc_Id=request.getParameter("locId");
			String FeeCodeDetails = request.getParameter("student")+","+academic_year+","+loc_Id;

			FeeCollectionVo collectionVo = new FeeCollectionBD().getTranportFeeCollectionAmount(FeeCodeDetails);
			
			request.setAttribute("FeeCollectionVo", collectionVo);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in FeeCollectionAction : TransportfeeCollectionStudentWise Ending");

		return mapping.findForward(MessageConstants.TRANSPORT_FEE_COLLECTION_STUDENT_WISE);
	}
	public synchronized ActionForward saveTransportFeeCollection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : saveTransportFeeCollection Starting");
		try {

			System.out.println("in saveFeeCollection ");

			String addmissionNo = request.getParameter("addmissionNo");
			String accYear = request.getParameter("accodemicyear");
			String termIdArray[] = request.getParameter("termid").split(",");
			String monthName[]=request.getParameter("monthName").split(",");
			String monthlyAmount[]=request.getParameter("monthlyAmount").split(",");
			String totalAmount=request.getParameter("totalAmount");
			String paymentParticulars=request.getParameter("paymentParticulars");
			String paymentMode=request.getParameter("paymentMode");
			String dd_cheque_bank=request.getParameter("dd_cheque_bank");
			String dd_cheque_date=request.getParameter("dd_cheque_date");
			String payingAmount=request.getParameter("payingAmount");
			
			FeeCollectionVo collectionVo = new FeeCollectionVo();
			collectionVo.setAddmissionno(addmissionNo);
			collectionVo.setAccYear(accYear);
			collectionVo.setTermIdArray(termIdArray);
			collectionVo.setMonthName(monthName);
			collectionVo.setMonthlyAmount(monthlyAmount);
			collectionVo.setUserID(HelperClass.getCurrentUserID(request));
			collectionVo.setTot_actual_amt(Double.parseDouble(totalAmount));
			collectionVo.setPaymentMode(paymentMode);
			collectionVo.setDd_cheque_bank(dd_cheque_bank);
			collectionVo.setDd_cheque_date(dd_cheque_date);
			collectionVo.setPaymentPatriculars(paymentParticulars);
			collectionVo.setCurrent_payment(Double.parseDouble(payingAmount));
			String status = new FeeCollectionBD().saveTransportFeeCollection(collectionVo);

			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : saveTransportFeeCollection Ending");

		return null;
	}
	public ActionForward feeDetailsListbyjs(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeDetailsList Starting");
		try {
			String locationId=request.getParameter("locationId");
			String academicYear=request.getParameter("academicYear");
			String search=request.getParameter("searchTerm");
			
			if(locationId.equalsIgnoreCase("all")){
				locationId="%%";
			}
			
			AddFeeVO vo = new AddFeeVO();

			vo.setName(search);
			vo.setAcademicYear(academicYear);
			vo.setLocationId(locationId);
			

			ArrayList<AddFeeVO> feelist = new FeeMasterDelegate().getfeedetails(vo);
			JSONObject obj=new JSONObject();
			obj.put("feelist", feelist);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeDetailsList Ending");

		return null;
	}
	
	public synchronized ActionForward refundTransportFeeCollection(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : refundTransportFeeCollection Starting");
		try {

			System.out.println("in saveFeeCollection ");

			String addmissionNo = request.getParameter("addmissionNo");
			String accYear = request.getParameter("accodemicyear");
			String termIdArray[] = request.getParameter("termid").split(",");
			String monthName[]=request.getParameter("monthName").split(",");
			String monthlyAmount[]=request.getParameter("monthlyAmount").split(",");
			String refundstatus=request.getParameter("refundstatus");
			String refrecieptNo=request.getParameter("refrecieptNo");
			
			
			FeeCollectionVo collectionVo = new FeeCollectionVo();

			collectionVo.setAddmissionno(addmissionNo);
			collectionVo.setAccYear(accYear);
			collectionVo.setTermIdArray(termIdArray);
			collectionVo.setMonthName(monthName);
			collectionVo.setMonthlyAmount(monthlyAmount);
			collectionVo.setUserID(HelperClass.getCurrentUserID(request));
			collectionVo.setRefundstatus(refundstatus);
			collectionVo.setRefrecieptNo(refrecieptNo);
			
			

			String status = new FeeCollectionBD().saveTransportFeeCollection(collectionVo);

			JSONObject object = new JSONObject();
			object.put("status", status);
			response.getWriter().print(object);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : refundTransportFeeCollection Ending");

		return null;
	}
	public  ActionForward FeeScholorship(
			ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in FeeCollectionAction : FeeScholorship Starting");
		try {

			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_FEE_SCHOLORSHIP);
			
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+ " Control in FeeCollectionAction : FeeScholorship Ending");

		return mapping.findForward(MessageConstants.FEE_SCHOLORSHIP);
	}
	
	
	public ActionForward addScholorshipStudent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeDetailsList Starting");
		try {
			String admissionNo=request.getParameter("admissionNo");
			String academicYear=request.getParameter("AcademicYearFor");
			String classId=request.getParameter("classId");
			String term=request.getParameter("term");
			String feecode=request.getParameter("feecode");
			String consfeeamount=request.getParameter("consfeeamount");
			String conType=request.getParameter("conType");
			
			StudentConcessionVo vo = new StudentConcessionVo();

			vo.setAcademicYear(academicYear);
			vo.setAdmissionNo(admissionNo);
			vo.setClassId(classId);
			vo.setConcessionAmount(consfeeamount);
			vo.setTermcode(term);
			vo.setContype(conType);
			vo.setFeecode(feecode);
			
			String status = new FeeCollectionDaoImpl().addScholorshipStudent(vo);
			JSONObject obj=new JSONObject();
			obj.put("status", status);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeDetailsList Ending");

		return null;
	}
	
	public ActionForward deleteScholorDetails(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeDetailsList Starting");
		try {
			String getDataArray[]=request.getParameter("getDataArray").split(",");
			String accYearArray[]=request.getParameter("accYearArray").split(",");
			
			AddFeeVO vo = new AddFeeVO();

			vo.setGetDataArray(getDataArray);
			vo.setAccYearArray(accYearArray);

			String status = new FeeCollectionDaoImpl().deleteScholorDetails(vo);
			JSONObject obj=new JSONObject();
			obj.put("status", status);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feeDetailsList Ending");

		return null;
	}
	
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
				StudentRegistrationVo registrationVo = new StudentRegistrationVo();
				String searchterm = request.getParameter("searchTerm");
				

				registrationVo.setSearchTerm(searchterm);
				
				FeeCollectionDaoImpl daoImpl = new FeeCollectionDaoImpl();
				searchStudentList = daoImpl.studentSearchbyadmissionNo(registrationVo);
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
	
	public  ActionForward getDefaulterFeeList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getDefaulterFeeList Starting");
		try {

			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_REPORTS_FEE_DEFAULTER_LIST);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_REPORTS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_REPORTS);
			
			
			System.out.println("in saveFeeCollection ");

			String locId = request.getParameter("locId");
			String classId = request.getParameter("classId");
			String divId = request.getParameter("divId");
			String termId = request.getParameter("termId");
			String accId = request.getParameter("accId");
			
			if(locId.equalsIgnoreCase("all") || locId.equalsIgnoreCase("")){
				locId="%%";
			};
			if(classId.equalsIgnoreCase("all") || classId.equalsIgnoreCase("")){
				classId="%%";
			};
			if(divId.equalsIgnoreCase("all") || divId.equalsIgnoreCase("")){
				divId="%%";
			};
			if(termId.equalsIgnoreCase("all") || termId.equalsIgnoreCase("")){
				termId="%%";
			};
			if(accId.equalsIgnoreCase("all") || accId.equalsIgnoreCase("")){
				accId="%%";
			};
			ArrayList<AddFeeVO> list = new FeeCollectionBD().getDefaulterFeeList(locId,classId,divId,termId,accId);
			
			JSONObject obj = new JSONObject();
			obj.put("data", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getDefaulterFeeList Ending");

		return null;
	}
	
	public  ActionForward getAdvanceOrBalanceForTransportFee(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getAdvanceOrBalanceForTransportFee Starting");
		try {

			String stuId = request.getParameter("stuId");
			
			ArrayList<AddFeeVO> list = new FeeCollectionDaoImpl().getAdvanceOrBalanceForTransportFee(stuId);
			
			JSONObject obj = new JSONObject();
			obj.put("data", list);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : getAdvanceOrBalanceForTransportFee Ending");

		return null;
	}
	
	
	public  ActionForward feeCancellation(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : feeCancellation Starting");
		try {

			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME, 
					LeftMenusHighlightMessageConstant.MODULE_FEE_CANCELLATION);
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_FEE);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);

			ArrayList<ReportMenuVo> accYearList = new ReportsMenuBD().getAccYears();
			request.setAttribute("AccYearList", accYearList);
			
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : feeCancellation Ending");

		return mapping.findForward("feeCancellation");
	}
	
	
	public  ActionForward feeCancellationList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : feeCancellation Starting");
		try {

			
			String studentId = request.getParameter("studentId");
			String accYear=request.getParameter("accYear");
			ArrayList<AddFeeVO> list = new FeeCollectionDaoImpl().getfeeCancellationList(studentId,accYear);
			
			JSONObject obj = new JSONObject();
			obj.put("data", list);
			response.getWriter().print(obj);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : feeCancellation Ending");

		return null;
	}
	
	public  ActionForward cancelFee(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : cancelFee Starting");
		try {

			
			String feecode = request.getParameter("feecode");
		
			String status= new FeeCollectionDaoImpl().cancelFee(feecode);
			
			JSONObject obj = new JSONObject();
			obj.put("status", status);
			response.getWriter().print(obj);
			

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : cancelFee Ending");

		return null;
	}
	
	public ActionForward addScholorshipStudentForEqual(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : addScholorshipStudentForEqual Starting");
		try {
			String admissionNo=request.getParameter("admissionNo");
			String academicYear=request.getParameter("AcademicYearFor");
			String classId=request.getParameter("classId");
			String term=request.getParameter("term");
			String feecode=request.getParameter("feecode");
			String consfeeamount=request.getParameter("consfeeamount");
			String conType=request.getParameter("conType");
			
			StudentConcessionVo vo = new StudentConcessionVo();

			vo.setAcademicYear(academicYear);
			vo.setAdmissionNo(admissionNo);
			vo.setClassId(classId);
			vo.setConcessionAmount(consfeeamount);
			vo.setTermcode(term);
			vo.setContype(conType);
			vo.setFeecode(feecode);
			
			String status = new FeeCollectionDaoImpl().addScholorshipStudentForEqual(vo);
			JSONObject obj=new JSONObject();
			obj.put("status", status);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : addScholorshipStudentForEqual Ending");

		return null;
	}
	
	
	public ActionForward saveSpecialFee(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
					throws Exception {
		

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : saveSpecialFee Starting");
		try {
			String studentId=request.getParameter("studentId");
			String academicYear=request.getParameter("accyearId");
			String feecode=request.getParameter("feeCode");
			String feeAmount=request.getParameter("feeAmount");
			String term=request.getParameter("term");
			
			StudentConcessionVo vo = new StudentConcessionVo();

			vo.setAcademicYear(academicYear);
			vo.setStudentId(studentId);
			vo.setConcessionAmount(feeAmount);
			vo.setFeecode(feecode);
			vo.setTerm(term);
			String status = new FeeCollectionDaoImpl().addSpecialFee(vo);
			JSONObject obj=new JSONObject();
			obj.put("status", status);
			response.getWriter().print(obj);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in FeeCollectionAction : saveSpecialFee Ending");

		return null;
	}
}
