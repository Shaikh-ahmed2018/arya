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

import com.centris.campus.daoImpl.StaffAttendanceDaoImpl;
import com.centris.campus.delegate.DepartmentMasterBD;
import com.centris.campus.delegate.StaffAttendanceBD;
import com.centris.campus.pojo.StaffAttendancePojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.DepartmentMasterVO;
import com.centris.campus.vo.StaffAttendanceVo;

public class StaffAttendanceAction extends DispatchAction {
	
	private static final Logger logger = Logger.getLogger(StaffAttendanceAction.class);
	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	
	public ActionForward staffattendaceUpload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : staffattendaceUpload Starting");
		request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
				LeftMenusHighlightMessageConstant.MODULE_STAFF_STAFFATTENDANCE);
		
		
		String todayDate=HelperClass.getCurrentSqlDate().toString();
		
		ArrayList<DepartmentMasterVO> departmentList=new DepartmentMasterBD().getDepartmentDetails();
		
		request.setAttribute("departmentList", departmentList);
		
		ArrayList<StaffAttendanceVo> attendanceList= new StaffAttendanceBD().getStaffAttendance(todayDate,"%%");
		
		request.setAttribute("attendanceList", attendanceList);
		request.setAttribute("TodayDate", HelperClass.convertDatabaseToUI(todayDate));
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : staffattendaceUpload Ending");
	
		
		return mapping.findForward(MessageConstants.StaffAttendance_upload);
	}
	
	public ActionForward searchStaffAttendaceUpload(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchStaffAttendaceUpload Starting");
		
		
		String departmentname = request.getParameter("department");
		String attendanceDate = request.getParameter("attDate");
		
		ArrayList<DepartmentMasterVO> departmentList=new DepartmentMasterBD().getDepartmentDetails();
		
		request.setAttribute("departmentList", departmentList);
		
		request.setAttribute("departmentname",departmentname);
		
		if(departmentname.equalsIgnoreCase("all")){
			
			departmentname="%%";
		}
		
		ArrayList<StaffAttendanceVo> attendanceList= new StaffAttendanceBD().getStaffAttendance(HelperClass.convertUIToDatabase(attendanceDate),departmentname);
		
		request.setAttribute("attendanceList", attendanceList);
		request.setAttribute("TodayDate",attendanceDate);
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : searchStaffAttendaceUpload Ending");
	
		
		return mapping.findForward(MessageConstants.StaffAttendance_upload);
	}
	
	public ActionForward updateAttendanceStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : updateAttendanceStatus Starting");
		
		String userId=HelperClass.getCurrentUserID(request);
		
		String date = request.getParameter("date");
		String teacherId=request.getParameter("teacherIdArray");
		String attendanceStatus=request.getParameter("statusArray");
		
		System.out.println("date :: "+date);
		System.out.println("teacherId :: "+teacherId);
		System.out.println("attendanceStatus :: "+attendanceStatus);
		
		StaffAttendancePojo attendancepojo=new StaffAttendancePojo();
		
		attendancepojo.setDate(date);
		attendancepojo.setTeacherId(teacherId);
		attendancepojo.setStatus(attendanceStatus);
		attendancepojo.setUserId(userId);
		
		String status=new StaffAttendanceBD().updateAttendanceStatus(attendancepojo);
		
		JSONObject object=new JSONObject();
		object.put("status", status);
		
		response.getWriter().print(object);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : updateAttendanceStatus Ending");
	
		
		return null;
	}
	
	public ActionForward RefreshAttendanceStatus(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
				
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : updateAttendanceStatus Starting");
		
		String userId=HelperClass.getCurrentUserID(request);
		
		String status=new StaffAttendanceDaoImpl().RefreshAttendanceStatus(userId);
		
		JSONObject object=new JSONObject();
		object.put("status", status);
		
		response.getWriter().print(object);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : updateAttendanceStatus Ending");
	
		
		return null;
	}
	
	public ActionForward downloadStaffAttendanceDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StaffAttendanceAction : downloadStaffAttendanceDetailsXLS  Starting");
		
		try {
		
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/staffattendancedetailsxls.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
			String startdate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");

			ArrayList<StaffAttendanceVo> staffAttendanceList = new StaffAttendanceBD().getStaffAttendanceList(startdate,endDate);

			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					staffAttendanceList);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/staffattendancedetailsxls.xls"));
			FileOutputStream fos = new FileOutputStream(outputFile);
			String[] sheetNames = { "Staff Attendance Details Excel Report" };
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
					request.getRealPath("Reports/staffattendancedetailsxls.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StaffAttendanceDetailsxls.xls");
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
				+ " Control in StaffAttendanceAction : downloadStaffAttendanceDetailsXLS   Ending");
				return null;
		
		
	}
	
	public ActionForward downloadStaffAttendanceDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in StaffAttendanceAction : downloadStaffAttendanceDetailsPDF  Starting");

			try {

				System.out.println("downloading pdf");
				String startdate = request.getParameter("startDate");
				String endDate = request.getParameter("endDate");

				ArrayList<StaffAttendanceVo> staffAttendanceList = new StaffAttendanceBD().getStaffAttendanceList(startdate,endDate);

			
				
				String sourceFileName = request
						.getRealPath("Reports/staffattendanceDetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						staffAttendanceList);

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
						+ "StaffAttendanceDetailsPDF - " + ".pdf\"");

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
					+ " Control in StaffAttendanceAction : downloadStaffAttendanceDetailsPDF  Ending");
			return null;

		}


}
