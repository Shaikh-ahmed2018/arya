package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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

import com.centris.campus.delegate.StudentPramotionBD;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.pojo.StudentPromotionPOJO;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AcadamicYearVO2;
import com.centris.campus.vo.StudentPramotionVO;

public class StudentPramotionAction extends DispatchAction {
	private static final Logger logger = Logger
			.getLogger(StudentPramotionAction.class);
	String result;
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	public ActionForward getAcadamicYear(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionAction : getAcadamicYear Starting");

		try {
			List<AcadamicYearVO2> yeardetailslist = new ArrayList<AcadamicYearVO2>();
			yeardetailslist = new StudentPramotionBD().getAcadamicYear();

			JSONObject jsonobject = new JSONObject();
			jsonobject.accumulate("yearDetails", yeardetailslist);
			response.getWriter().print(jsonobject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionAction : getAcademicYear Ending");

		return null;
	}

	public ActionForward searchStudent(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionAction : searchStudent Starting");

		try {

			List<StudentPramotionVO> studentlist = new ArrayList<StudentPramotionVO>();

			String acadamicYear = request.getParameter("acadamicyear");
			String section = request.getParameter("section");
			studentlist = new StudentPramotionBD().getStudentData(acadamicYear,
					section);
			/*System.out
					.println("studentlist::::::::::::::::::::::::::::::::::::::: "
							+ studentlist.size());*/
			JSONObject jsonobject = new JSONObject();
			jsonobject.accumulate("studentList", studentlist);
			response.getWriter().print(jsonobject);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionAction : searchStudent Ending");

		return null;
	}

	public ActionForward insertStudentPromotion(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionAction : insertStudentPromotion Starting");
		try {

			ArrayList<StudentPramotionVO> studentList = new ArrayList<StudentPramotionVO>();

			Enumeration enumeration = request.getParameterNames();
			while (enumeration.hasMoreElements()) {
				String val = (String) enumeration.nextElement();
			}
			// Getting user
			String createUser = HelperClass.getCurrentUser(request);

			String[] studentidval = request.getParameterValues("studentidvalues[]");
			
			String fromclassval = request.getParameter("fromclass");
			String[] toclassval = request.getParameterValues("toclassvalues[]");
			String fromacadamicyearval = request.getParameter("fromacadamicyear");
			String[] toacadamicyearval = request.getParameterValues("toacadamicyearvalues[]");
			String fromsectionval = request.getParameter("fromsection");
			String[] tosectionvaluesval = request.getParameterValues("tosectionvalues[]");
			String[] promotionstatus = request.getParameterValues("pramotionstatus[]");
			String[] toSpecialization=request.getParameterValues("toSpecialization[]");
			String[] fromSpecialization=request.getParameterValues("fromSpecialization[]");

			for (int i = 0; i < studentidval.length; i++) {
				for (int j = i; j <= i; j++) {
					for (int k = j; k <= j; k++) {
						for (int l = k; l <= k; l++) {
							for (int m = l; m <= l; m++) {
								for (int n = m; n <= m; n++) {
									StudentPromotionPOJO studentPramotionPOJO = new StudentPromotionPOJO();
									studentPramotionPOJO
											.setStudentid(studentidval[i]
													.split(","));
									studentPramotionPOJO.setFromSpecialization(fromSpecialization[m].split(","));
									studentPramotionPOJO.setToSpecialization(toSpecialization[m].split(","));
									studentPramotionPOJO
											.setToclass(toclassval[k]
													.split(","));
									studentPramotionPOJO
											.setToacadamicyear(toacadamicyearval[l]
													.split(" "));
									studentPramotionPOJO
											.setTosection(tosectionvaluesval[j]
													.split(","));
									studentPramotionPOJO
											.setPromotionstatus(promotionstatus[n]
													.split(","));
									studentPramotionPOJO
											.setFromacadamicyear(fromacadamicyearval);
									
									studentPramotionPOJO
											.setFromclass(fromclassval);
									studentPramotionPOJO
											.setFromsection(fromsectionval);
									studentPramotionPOJO
											.setCreateuser(createUser);
								

									StudentPramotionVO studentVo = new StudentPramotionBD()
											.insertStudentPromotion(studentPramotionPOJO);
									if (studentVo.getAdmissionno() != null) {
										studentList.add(studentVo);
									}

								}
							}
						}
					}
				}
			}

			request.getSession(false).setAttribute("notpromotedStud",
					studentList);
			JSONObject jsonobject = new JSONObject();
			jsonobject.put("RESULT", studentList);
			response.getWriter().print(jsonobject);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionAction : insertStudentPromotion Ending");
		return null;
	}

	public ActionForward downloadStudentDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionAction : downloadStudentDetailsXLS  Starting");
		
		try {
		
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/studentpromotiondetailsxls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			ArrayList<StudentPramotionVO> StudentPramotionlist = new StudentPramotionBD()
			.getpromotionslist();
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					StudentPramotionlist);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/studentpromotiondetailsxls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Stream Details Excel Report" };
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
					request.getRealPath("Reports/studentpromotiondetailsxls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=studentpromotiondetailsxls.xls");
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
				+ " Control in StudentPramotionAction : downloadStudentDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward downloadStudentDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StudentPramotionAction : downloadStudentDetailsPDF  Starting");

			try {

				/*System.out.println("downloading pdf");*/

				ArrayList<StudentPramotionVO> StudentPramotionlist = new StudentPramotionBD()
				.getpromotionslist();
				
				String sourceFileName = request
						.getRealPath("Reports/studentpromotionDetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						StudentPramotionlist);

				String PropfilePath = getServlet().getServletContext().getRealPath(
						"")
						+ "\\images\\" + ImageName.trim();

				String schName = res.getString("SchoolName");
				String schAddLine1 = res.getString("AddressLine1");

				Map parameters = new HashMap();
				
				parameters.put("Image", PropfilePath);


				/*parameters.put("Image", clientImage);

				parameters.put("ClientName", ClientName);

				parameters.put("ClientAddress", ClientAddress_l1);*/

				byte[] bytes = JasperRunManager.runReportToPdf(jasperreport,
						parameters, beanColDataSource);

				response.setContentType("application/pdf");

				response.setContentLength(bytes.length);

				response.setHeader("Content-Disposition", "outline; filename=\""
						+ "studentpromotionDetailsPDF - " + ".pdf\"");

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
					+ " Control in StudentPramotionAction : downloadStudentDetailsPDF  Ending");
			return null;

		}

	
	public ActionForward studentPromotionList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionAction : searchStudent Starting");

		try {

			List<StudentPramotionVO> studentlist = new ArrayList<StudentPramotionVO>();

			String acadamicYear = request.getParameter("accYear");
			String classId = request.getParameter("classId");
			String specializationId=request.getParameter("specializationId");
			String section =classId+","+request.getParameter("sectionId")+","+specializationId;
			studentlist = new StudentPramotionBD().getStudentData(acadamicYear,section);
			
			
			request.setAttribute("studentList", studentlist);
			request.setAttribute("acadamicYear", acadamicYear);
			request.setAttribute("classId", classId);
			request.setAttribute("sectionId", request.getParameter("sectionId"));
			request.setAttribute("specializationId", specializationId);
			request.setAttribute("accyearname", studentlist.get(0).getAcadamicyear());
			request.setAttribute("nextaccyearname", studentlist.get(0).getNextyear());
			request.setAttribute("nextaccyearid", studentlist.get(0).getNextyearId());
			request.setAttribute("classname", studentlist.get(0).getClassname());
			request.setAttribute("sectionname", studentlist.get(0).getSectionname());
			request.setAttribute("specializationname", request.getParameter("specName"));
			request.setAttribute("classStearm", studentlist.get(0).getCategoryid());
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StudentPramotionAction : searchStudent Ending");

		return mapping.findForward("studentpromotionscreens");
	}


}