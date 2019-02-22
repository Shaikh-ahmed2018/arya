package com.centris.campus.actions;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import com.centris.campus.delegate.ClassFeeSetupBD;
import com.centris.campus.delegate.StudentRegistrationDelegate;
import com.centris.campus.delegate.UploadStudentXSLBD;
import com.centris.campus.forms.ClassFeeSetupForm;
import com.centris.campus.pojo.ClassFeeSetupPojo;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.util.FeeExcelUploadUtil;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LeftMenusHighlightMessageConstant;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.UploadStudentXSLUtility;
import com.centris.campus.vo.ClassFeeSetupVo;
import com.centris.campus.vo.StudentRegistrationVo;
import com.centris.campus.vo.UploadStudentXlsVO;
import com.centris.campus.vo.UserDetailVO;

public class UploadFeeCollectionDetails extends DispatchAction{

	private static final Logger logger = Logger.getLogger(UploadFeeCollectionDetails.class);

	// For Fee and Transport Fee
	
	public ActionForward feeCollectionUpload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadFeeCollectionDetailsAction  : FeeCollectionExcelUpload : Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_FEE_EXCELUPLOAD);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadFeeCollectionDetailsAction : FeeCollectionExcelUpload : Ending");

		return mapping.findForward(MessageConstants.FEECOLLECTION_EXCEL_UPLOAD);
	}
	
	public ActionForward downloadfileformat(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadFeeCollectionDetailsAction  : downloadfileformat : Starting");
		try{
			  
			  String filePath = request.getRealPath("/") +"FIles/FeeCollectionFileUpload/FeeCollectionForm.xlsx"; 
			  
			  System.out.println("FILEPATH:::"+filePath);
			  ServletOutputStream out  = response.getOutputStream();
			  FileInputStream in = new
			  FileInputStream(filePath); 
			  HttpSession ses = request.getSession();
			  response.setContentType("application/vnd.ms-excel");
			  response.addHeader("content-disposition", "attachment; filename="+"FeeCollectionForm.xlsx");
			  
			  int octet; while ((octet = in.read()) != -1) out.write(octet);
			  
			  in.close(); out.close();
			  
			  }catch(Exception e){
			
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadFeeCollectionDetailsAction : downloadfileformat : Ending");
		
		return null;
	}
	
	public ActionForward insertFeeXSL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in UploadFeeCollectionDetailsAction  : insertFeeXSL : Starting");
		String forward=null;
		int notInsertCount = 0;
		int beforeInsert = 0;
		int successInsert = 0;
		try{
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_FEE);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_FEE);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_FEE_EXCELUPLOAD);
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String username = userDetailVO.getUserId();
			
			String fileName =null;
		
			ClassFeeSetupForm classform = (ClassFeeSetupForm)form;
			FormFile file = classform.getFormfile();
			String filePath = request.getRealPath("/");
			if (file != null) {

				fileName = file.getFileName();

				File fileURL = new File(filePath,fileName);
				request.setAttribute("fileAttribute",fileName);

				Map<String, Object> studentMap = new FeeExcelUploadUtil().getExcelData(fileURL,file);

				List<ClassFeeSetupPojo> alList = (List<ClassFeeSetupPojo>) studentMap.get("List");
				

				beforeInsert = alList.size();
				

				ClassFeeSetupBD classXSLBD = new ClassFeeSetupBD();

				Set<ClassFeeSetupVo> classXLSList = new HashSet<ClassFeeSetupVo>();
				String demo = (String) studentMap.get("Error");
				classXLSList = classXSLBD.insertStudentXSL((List<ClassFeeSetupPojo>) studentMap.get("List"),username,demo);
		
				notInsertCount = classXLSList.size();
				
				successInsert = beforeInsert - notInsertCount;
				
				
				//new code
				
				if (classXLSList.size() != 0) {
					request.setAttribute("FailFeeList",classXLSList);
					request.setAttribute("failedStaffList",	request.getAttribute("failedStaffList"));
					request.setAttribute("errorMessage",notInsertCount+": Duplicate or Invalid record(s) found.");
					forward=MessageConstants.FEECOLLECTION_EXCEL_UPLOAD;
					
				} else if (demo != null) {
					request.setAttribute("successmessagediv", demo);
					forward=MessageConstants.FEECOLLECTION_EXCEL_UPLOAD;

				} else {
					successInsert = beforeInsert - notInsertCount;
					System.out.println("Total SuccessInsert= "+successInsert);
					request.setAttribute("successmessagediv", +successInsert+ ":FeeCollection Records Inserted SuccessFully");
					forward=MessageConstants.FEECOLLECTION_EXCEL_UPLOAD;
				}
			}
			else {
				forward=MessageConstants.FEECOLLECTION_EXCEL_UPLOAD;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+"Control in UploadFeeCollectionDetailsAction : insertFeeXSL : Ending");
		return mapping.findForward(forward);
	}
	
	public ActionForward transportFeeCollectionUpload(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadFeeCollectionDetailsAction  : transportFeeCollectionUpload : Starting");

		try {
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_UPLOADTRANSPORTFEEEXCELDATAFILE);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadFeeCollectionDetailsAction : transportFeeCollectionUpload : Ending");

		return mapping.findForward(MessageConstants.TRANSPORT_FEECOLLECTION_EXCEL_UPLOAD);
	}
	
public ActionForward downloadtransportfileformat(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadFeeCollectionDetailsAction  : downloadtransportfileformat : Starting");
		try{
			  
			  String filePath = request.getRealPath("/") +
			  "FIles/FeeCollectionFileUpload/TransportFeeCollection.xlsx"; 
			  
			  System.out.println("FILEPATH:::"+filePath);
			  ServletOutputStream out  = response.getOutputStream();
			  FileInputStream in = new
			  FileInputStream(filePath); 
			  HttpSession ses = request.getSession();
			  response.setContentType("application/vnd.ms-excel");
			  response.addHeader("content-disposition", "attachment; filename="+"TransportFeeCollectionForm.xlsx");
			  
			  int octet; while ((octet = in.read()) != -1) out.write(octet);
			  
			  in.close(); out.close();
			  
			  }catch(Exception e){
			
		}
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadFeeCollectionDetailsAction : downloadtransportfileformat : Ending");
		
		return null;
	}
	public ActionForward insertTransportFeeXSL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		System.out.println("Inside Action");
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date()) + MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date()) + " Control in UploadFeeCollectionDetailsAction  : insertTransportFeeXSL : Starting");
		String forward=null;
		int notInsertCount = 0;
		int beforeInsert = 0;
		int successInsert = 0;
		try{
			
			request.setAttribute(MessageConstants.MODULE_NAME,MessageConstants.BACKOFFICE_TRANSPORT);
			request.setAttribute(MessageConstants.HIGHLIGHT_NAME,MessageConstants.MODULE_TRANSPORT);
			request.setAttribute(LeftMenusHighlightMessageConstant.SUBMODULE_HIGHLIGHT_NAME,LeftMenusHighlightMessageConstant.MODULE_TRANSPORT_UPLOADTRANSPORTFEEEXCELDATAFILE);
				
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String username = userDetailVO.getUserId();
			
			String fileName =null;
		
			ClassFeeSetupForm classform = (ClassFeeSetupForm)form;
			FormFile file = classform.getFormfile();
			String filePath = request.getRealPath("/");
			if (file != null) {

				fileName = file.getFileName();

				File fileURL = new File(filePath,fileName);
				request.setAttribute("fileAttribute",fileName);

				Map<String, Object> studentMap = new FeeExcelUploadUtil().getTransportExcelData(fileURL,file);

				List<ClassFeeSetupPojo> alList = (List<ClassFeeSetupPojo>) studentMap.get("List");
				
				System.out.println("Action class >> size of list after utility class: "+alList.size());

				beforeInsert = alList.size();
				
				System.out.println("beforeInsert:::"+beforeInsert);

				ClassFeeSetupBD classXSLBD = new ClassFeeSetupBD();

				Set<ClassFeeSetupVo> classXLSList = new HashSet<ClassFeeSetupVo>();
				String demo = (String) studentMap.get("Error");
				classXLSList = classXSLBD.insertTransportStudentXSL((List<ClassFeeSetupPojo>) studentMap.get("List"),username,demo);
		
				notInsertCount = classXLSList.size();
				
				successInsert = beforeInsert - notInsertCount;
				
				System.out.println("notInsertCount:::"+notInsertCount);
				System.out.println("After Insert::::"+classXLSList.size());
				for (ClassFeeSetupVo s : classXLSList ) {
				    System.out.println("Value: " +s.getStuFname());
				}
				
				//new code
				
				if (classXLSList.size() != 0) {
					request.setAttribute("FailFeeList",classXLSList);
					request.setAttribute("failedStaffList",	request.getAttribute("failedStaffList"));
					request.setAttribute("errorMessage",successInsert+": record(s) uploaded, "+notInsertCount+": Duplicate or Invalid record(s) found.");
					forward=MessageConstants.TRANSPORT_FEECOLLECTION_EXCEL_UPLOAD;
					
				} else if (demo != null) {
					request.setAttribute("successmessagediv", demo);
					forward=MessageConstants.TRANSPORT_FEECOLLECTION_EXCEL_UPLOAD;

				} else {
					successInsert = beforeInsert - notInsertCount;
					System.out.println("Total SuccessInsert= "+successInsert);
					request.setAttribute("successmessagediv", +successInsert+ ":FeeCollection Records Inserted SuccessFully");
					forward=MessageConstants.TRANSPORT_FEECOLLECTION_EXCEL_UPLOAD;
				}
			}
			/*else {
				forward=MessageConstants.FEECOLLECTION_EXCEL_UPLOAD;
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}
		JLogger.log(0, JDate.getTimeString(new Date())+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())+"Control in UploadFeeCollectionDetailsAction : insertTransportFeeXSL : Ending");
		return mapping.findForward(forward);
	}
	
	
}
