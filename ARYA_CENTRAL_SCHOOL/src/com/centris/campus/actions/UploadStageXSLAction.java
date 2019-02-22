package com.centris.campus.actions;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
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
import org.json.JSONArray;
import org.json.JSONObject;

import com.centris.campus.daoImpl.UploadStageXLSDaoImpl;
import com.centris.campus.daoImpl.UploadStudentXLSDaoImpl;
import com.centris.campus.delegate.StageDelegateBD;
import com.centris.campus.delegate.UploadStageXSLBD;
import com.centris.campus.delegate.UploadStudentXSLBD;
import com.centris.campus.forms.UploadStageXSLForm;
import com.centris.campus.pojo.UploadStageXlsPOJO;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.UploadStageXSLUtility;
import com.centris.campus.util.UploadStudentXSLUtility;
import com.centris.campus.vo.AddStageVO;
import com.centris.campus.vo.UploadStageXlsVO;
import com.centris.campus.vo.UploadStudentXlsVO;
import com.centris.campus.vo.UserDetailVO;

public class UploadStageXSLAction extends DispatchAction {

	private static Logger logger = Logger
			.getLogger(UploadStageXSLAction.class);


	 
	public ActionForward insertStageXSL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLAction : insertStageXSL : Starting");
		int notInsertCount = 0;
		int beforeInsert = 0;
		int successInsert = 0;
		String fileName = null;
		String forward=null;
		try {
			
			
			System.out.println("InsertStageXSL Action Is Working");

			UploadStageXLSDaoImpl daoImpl = new UploadStageXLSDaoImpl();

			int countBeforeInsert = daoImpl.checkStageCountBeforeInsert();

			UserDetailVO userDetailVO = (UserDetailVO) request
					.getSession(false).getAttribute("UserDetails");

			String username = userDetailVO.getUserId();

			System.out.println("username:::" +username);

			UploadStageXSLForm uploadStageXSLForm = (UploadStageXSLForm) form;

			FormFile file = uploadStageXSLForm.getFormFile();

			String filePath = request.getRealPath("/");

			if (file != null) {

				fileName = file.getFileName();

				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);
				
				System.out.println("File name in Upload Stage Action class  "+fileName);
				
				System.out.println("File path in Upload Stage Action class  "+filePath);
				
				Map<String, Object> stageMap = new UploadStageXSLUtility().getExcelData(fileURL, file);

				List<UploadStageXlsPOJO> alList = (List<UploadStageXlsPOJO>) stageMap.get("List");
				System.out.println("size of alList Upload Stage Action class  "+alList.size());
				
				
				for (UploadStageXlsPOJO uploadStageXlsPOJO : alList) {
					
					System.out.println("name;:::"+uploadStageXlsPOJO.getStage_name());
					
					
				}

				beforeInsert = alList.size();
				
				System.out.println("beforeInsert in excel file::: "+beforeInsert);

				UploadStageXSLBD stageXSLBD = new UploadStageXSLBD();

				Set<UploadStageXlsVO> stageXLSList = new HashSet<UploadStageXlsVO>();
				
				
				String demo = (String) stageMap.get("Error");
				System.out.println("demo: "+demo);
				
				System.out.println("Going To Delegate");

				
				stageXLSList = stageXSLBD.insertStageXSL((List<UploadStageXlsPOJO>) stageMap.get("List"),username, demo);
				
				notInsertCount = stageXLSList.size();
				
				successInsert = beforeInsert - notInsertCount;
				
				System.out.println("notInsertCount:::"+notInsertCount);
				
				

				if (stageXLSList.size() != 0) {

					request.setAttribute("failedStageList", stageXLSList);

					request.setAttribute("failedStageList",
					request.getAttribute("failedStageList"));
					request.setAttribute("errorMessage",successInsert+": record(s) uploaded, "+notInsertCount+": Duplicate or Invalid record(s) found.");
					forward=MessageConstants.STAGE_EXCEL_UPLOAD;
					
				} else if (demo != null) {
					request.setAttribute("successmessagediv", demo);
					forward=MessageConstants.STAGE_EXCEL_UPLOAD;

				} else {
					//successInsert = beforeInsert - notInsertCount;
					System.out.println("Total SuccessInsert= "+successInsert);
					request.setAttribute("successmessagediv", +successInsert+ ":Stage Rocords Registered SuccessFully");
					
					AddStageVO vo = new AddStageVO();

					ArrayList<AddStageVO> list = new StageDelegateBD().StageDetails(HelperClass.getCurrentYearID());
					request.setAttribute("stageAccyear", HelperClass.getCurrentYearID());
					request.setAttribute("StageDetails", list);
					forward=MessageConstants.STAGE_EXCEL_UPLOAD;
				}

			} else {

				forward=MessageConstants.STAGE_EXCEL_UPLOAD;
			}

		} catch (Exception e) {
			System.out.println("exception Block");
			request.setAttribute("errorMessage","File is Corrupted or Empty.");
			forward=MessageConstants.STAGE_EXCEL_UPLOAD;
			e.printStackTrace();
			logger.error(e.getMessage(), e);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLAction : insertStageXSL : Ending");

		return mapping.findForward(forward);

	}

	
	  public ActionForward downloadStageFileFormat(ActionMapping mapping, ActionForm
	  form,HttpServletRequest request, HttpServletResponse response) throws
	  Exception {
	  
	  logger.setLevel(Level.DEBUG); JLogger.log(0, JDate.getTimeString(new
	  Date()) + MessageConstants.START_POINT);
	  logger.info(JDate.getTimeString(new Date()) +
	  " Control in UploadStageXSLAction : downloadStageFileFormat : Starting");
	  
	  
	  System.out.println("downloadfileformat");
	  
	  
	  try {
	  
	  
	  
	  
	  String filePath = request.getRealPath("/") +"FIles/StageFileUpload/StageExcelFileFormat.xlsx"; 
	
	  
	  System.out.println("FILEPATH:::"+filePath);
	  
	  ServletOutputStream out  = response.getOutputStream(); FileInputStream in = new
	  FileInputStream(filePath); HttpSession ses = request.getSession();
	  response.setContentType("application/vnd.ms-excel");
	  response.addHeader("content-disposition", "attachment; filename=" +
	  "StageExcelFileFormat.xlsx");
	  
	  int octet; while ((octet = in.read()) != -1) out.write(octet);
	  
	  in.close(); out.close();
	  
	  } catch (Exception e) {
		  e.printStackTrace(); 
		  logger.error(e.getMessage(), e); 
	  }
	  
	  
	  JLogger.log(0, JDate.getTimeString(new Date()) +
	  MessageConstants.END_POINT); logger.info(JDate.getTimeString(new Date())
	  + " Control in UploadStageXSLAction : downloadStageFileFormat : Ending");
	  
	  return null;
	  
	  }
	 

}
