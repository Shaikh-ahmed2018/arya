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

import com.centris.campus.delegate.ReportsMenuBD;
import com.centris.campus.delegate.StreamDetailsBD;
import com.centris.campus.forms.StreamDetailsForm;
import com.centris.campus.pojo.ConcessionDetailsPojo;
import com.centris.campus.pojo.ExamDetailsPojo;
import com.centris.campus.pojo.RoleMasterPojo;
import com.centris.campus.pojo.StreamDetailsPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StreamDetailsVO;

public class StreamDetailsAction extends DispatchAction{
	
	private static final Logger logger = Logger
			.getLogger(StreamDetailsAction.class);

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");
	private static String ImageName = res.getString("ImageName");
	
	public ActionForward insertStreamDetailsAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : streamList Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		StreamDetailsForm detailsForm = new StreamDetailsForm();
		
		String createCode = HelperClass.getCurrentUserID(request);
		String locationname=request.getParameter("locationname");
		String streamid = request.getParameter("streamId");
		String streamName = request.getParameter("streamname");
		String description = request.getParameter("description");

		detailsForm.setStreamName(streamName);
		detailsForm.setDescription(description);
		detailsForm.setCreateUser(createCode);
		detailsForm.setStreamId(streamid);
		detailsForm.setLocationId(locationname);
		
		
		
		StreamDetailsBD detailsBD = new StreamDetailsBD();
		
		String result = detailsBD.insertStreamDetailsBD(detailsForm);
		
          JSONObject jsonobj = new JSONObject();
		
		jsonobj.put("jsonResponse", result);
		
		response.getWriter().print(jsonobj);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : insertStreamDetailsAction  Ending");
	return null;

	}
	
	public ActionForward editStreamDetailsAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : editStreamDetailsAction  Starting");
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,
					LeftMenusHighlightMessageConstant.MODULE_SETTINGS_STREAMDETAILS);
			
			String title ="Modify Stream";
			request.setAttribute("title", title);
			ArrayList<ReportMenuVo> locationList = new ReportsMenuBD().getlocationList();
			request.setAttribute("locationList", locationList);
			
			String streamid = request.getParameter("streamid");
			StreamDetailsVO detailsVo = new StreamDetailsVO();
			detailsVo.setStreamId(streamid);	
			StreamDetailsBD detailsBD = new StreamDetailsBD();
			StreamDetailsVO ckeck = detailsBD.editStreamDetailsBD(detailsVo);
			request.setAttribute("list", ckeck);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : editStreamDetailsAction   Ending");
		
		 return mapping.findForward(MessageConstants.ADD_STREAM);
	
	}
	
	public ActionForward deleteStreamDetailsAction(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : deleteStreamDetailsAction  Starting");
	
		
		try {
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		String streamid[] = request.getParameterValues("streamid[]");
		
		StreamDetailsVO detailsVo = new StreamDetailsVO();
		/*detailsVo.setStreamId(streamid);*/
		
		
		StreamDetailsBD detailsBD = new StreamDetailsBD();
		
		String ckeck = detailsBD.deleteStreamDetailsBD(streamid);
		request.setAttribute("delete", ckeck); 
		
		JSONObject json= new JSONObject();
		
		json.put("status", ckeck);
	
		response.getWriter().print(json);
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : deleteStreamDetailsAction   Ending");
		
		return null;
		
	}
	
	
	public ActionForward searchstreamdetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : searchStreamDetailsAction  Starting");
		
		try {
			
			String SearchName = request.getParameter("searchname");
			
		
			request.setAttribute(MessageConstants.MODULE_NAME,
					MessageConstants.BACKOFFICE_SETTINGS);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,
					MessageConstants.MODULE_SETTINGS);
			
			 
			ArrayList<StreamDetailsVO> getstreamlist = new StreamDetailsBD().searchStreamDetailsBD(SearchName);
			
			request.setAttribute("streamDetailsList", getstreamlist);
			request.setAttribute("searchname", SearchName);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : searchStreamDetailsAction   Ending");
		
		 return mapping.findForward(MessageConstants.STREAM);
	}
	
	
	public ActionForward validateStreamName(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : validateStreamStreamDetailsAction  Starting");
		
		
		
		String streamname = (String) request.getParameter("streamname");
		String locationId=request.getParameter("locationId");
		String streamId= request.getParameter("streamId");
		
		StreamDetailsVO detailsVo = new StreamDetailsVO();
		
		detailsVo.setStreamId(streamId);
		detailsVo.setLocationId(locationId);
		detailsVo.setStreamname(streamname);
		
		boolean streamname_Available= new StreamDetailsBD().validateStreamNameBD(detailsVo);
		
			
		JSONObject jsonobject= new JSONObject();
		
		if(streamname_Available){
		
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
	
	
	public ActionForward downloadStreamDetailsXLS(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in StreamDetailsAction : downloadStreamDetailsAction  Starting");
		
		try {
		
			File pdfxls = null;
			FileInputStream input = null;
			BufferedInputStream buf = null;
			ServletOutputStream stream = null;

			String sourceFileName = request
					.getRealPath("Reports/StreamDetailsXLSReport.jrxml");
			JasperDesign design = JRXmlLoader.load(sourceFileName);
			JasperReport jasperreport = JasperCompileManager
					.compileReport(design);
		
			
			String SearchName = request.getParameter("searchTerm");
			
		
			StreamDetailsBD obj = new StreamDetailsBD();
			List<StreamDetailsVO> list = new ArrayList<StreamDetailsVO>();
			
			
			if(SearchName != null){
				
			
				
				list=obj.searchStreamDetailsBD(SearchName);
				request.setAttribute("searchname", SearchName);
				
				
				
				
			}
			else{
				
			 list =obj.getStreamDetailsBD("%%");
				
			}
			
			
			
			
			
			JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
					list);
			Map parameters = new HashMap();
			
			
			stream = response.getOutputStream();
			JasperPrint print = JasperFillManager.fillReport(jasperreport,
					parameters, beanColDataSource);
			JRXlsExporter exporter = new JRXlsExporter();
			File outputFile = new File(
					request.getRealPath("Reports/StreamDetailsXLSReport.xls"));
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
					request.getRealPath("Reports/StreamDetailsXLSReport.xls"));
			response.setContentType("application/octet-stream");
			response.addHeader("Content-Disposition",
					"attachment; filename=StreamDetailsXLSReport.xls");
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
	
	public ActionForward downloadStreamDetailsPDF(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		

			logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in FeeConcession : ConcessionDetailsPDFReport  Starting");
			String schoolLocation = request.getSession(false).getAttribute("current_schoolLocation").toString();
			try {
	
				String SearchName = request.getParameter("searchTerm");
				
				
				
				StreamDetailsBD obj = new StreamDetailsBD();
				List<StreamDetailsVO> list = new ArrayList<StreamDetailsVO>();
				
				
				if(SearchName != null){
					
				
					
					list=obj.searchStreamDetailsBD(SearchName);
					request.setAttribute("searchname", SearchName);
					
					
					
					
				}
				else{
					
				 list =obj.getStreamDetailsBD(schoolLocation);
					
				}
				
				
				
				
				
				
				
				
				String sourceFileName = request
						.getRealPath("Reports/StreamDetailsPDF.jrxml");
				JasperDesign design = JRXmlLoader.load(sourceFileName);

				JasperReport jasperreport = JasperCompileManager
						.compileReport(design);

				JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(
						list);

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
						+ "StreamDetailsPDF - " + ".pdf\"");

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
}
