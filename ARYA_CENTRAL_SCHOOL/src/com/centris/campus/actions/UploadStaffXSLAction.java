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

import com.centris.campus.daoImpl.UploadStaffXLSDaoImpl;
import com.centris.campus.daoImpl.UploadStageXLSDaoImpl;
import com.centris.campus.daoImpl.UploadStudentXLSDaoImpl;
import com.centris.campus.delegate.StageDelegateBD;
import com.centris.campus.delegate.TeacherRegistrationBD;
import com.centris.campus.delegate.UploadStaffXSLBD;
import com.centris.campus.delegate.UploadStageXSLBD;
import com.centris.campus.delegate.UploadStudentXSLBD;
import com.centris.campus.forms.UploadStaffXSLForm;
import com.centris.campus.pojo.UploadStaffXlsPOJO;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.util.UploadStaffXSLUtility;
import com.centris.campus.util.UploadStageXSLUtility;
import com.centris.campus.util.UploadStudentXSLUtility;
import com.centris.campus.vo.AddStageVO;
import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.UploadStaffXlsVO;
import com.centris.campus.vo.UploadStageXlsVO;
import com.centris.campus.vo.UploadStudentXlsVO;
import com.centris.campus.vo.UserDetailVO;

public class UploadStaffXSLAction extends DispatchAction {

	private static Logger logger = Logger
			.getLogger(UploadStaffXSLAction.class);


	 
	public ActionForward insertStaffXSL(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStaffXSLAction : insertStageXSL : Starting");
		int notInsertCount = 0;
		int beforeInsert = 0;
		int successInsert = 0;
		String fileName = null;
		String forward=null;
		try {
			
			System.out.println("InsertStaffXSL Action Is Working");

			UploadStaffXLSDaoImpl daoImpl = new UploadStaffXLSDaoImpl();

			int countBeforeInsert = daoImpl.checkStaffCountBeforeInsert();

			UserDetailVO userDetailVO = (UserDetailVO) request
					.getSession(false).getAttribute("UserDetails");

			String username = userDetailVO.getUserId();

			System.out.println("username:::" +username);

			UploadStaffXSLForm uploadStaffXSLForm = (UploadStaffXSLForm) form;

			FormFile file = uploadStaffXSLForm.getFormFile();

			String filePath = request.getRealPath("/");

			if (file != null) {

				fileName = file.getFileName();

				File fileURL = new File(filePath, fileName);
				request.setAttribute("fileAttribute", fileName);
				
				System.out.println("File name in Upload Staff Action class  "+fileName);
				
				System.out.println("File path in Upload Staff Action class  "+filePath);
				
				Map<String, Object> staffMap = new UploadStaffXSLUtility().getExcelData(fileURL, file);

				List<UploadStaffXlsPOJO> alList = (List<UploadStaffXlsPOJO>) staffMap.get("List");
				
				//System.out.println("after Reading the data in Excel: size of records: "+alList.size());
				
				for (UploadStaffXlsPOJO UploadStaffXlsPOJO : alList) {
					
					System.out.println("name;:::"+UploadStaffXlsPOJO.getFirstName());
					
				}

				beforeInsert = alList.size();
				
				System.out.println("beforeInsert in excel file::: "+beforeInsert);

				UploadStaffXSLBD staffXSLBD = new UploadStaffXSLBD();

				Set<UploadStaffXlsVO> staffXLSList = new HashSet<UploadStaffXlsVO>();
				
				
				String demo = (String) staffMap.get("Error");
				System.out.println("demo: "+demo);
				
				System.out.println("Going To Delegate");

				
				staffXLSList = staffXSLBD.insertStaffXSL((List<UploadStaffXlsPOJO>) staffMap.get("List"),username, demo);
				
				notInsertCount = staffXLSList.size();
				
				successInsert = beforeInsert - notInsertCount;
				
				System.out.println("notInsertCount:::"+notInsertCount);
				System.out.println("+++++++Returned Back to UploadStaffExcelFile Action Class ++++++++");

				if (staffXLSList.size() != 0) {

					request.setAttribute("failedStaffList", staffXLSList);

					//request.setAttribute("failedStaffList",	request.getAttribute("failedStaffList"));
					request.setAttribute("errorMessage",successInsert+": record(s) uploaded, "+notInsertCount+": Duplicate or Invalid record(s) found.");
					
					
					forward=MessageConstants.STAFF_EXCEL_FILE_UPLOAD;
					
				} else if (demo != null) {
					request.setAttribute("successmessagediv", demo);
					forward=MessageConstants.STAFF_EXCEL_FILE_UPLOAD;

				} else {
					//successInsert = beforeInsert - notInsertCount;
					System.out.println("Total SuccessInsert= "+successInsert);
					request.setAttribute("successmessagediv", +successInsert+ ":Stage Rocords Registered SuccessFully");
					
					ArrayList<AllTeacherDetailsVo> list = new ArrayList<AllTeacherDetailsVo>();
					list = new TeacherRegistrationBD().getAllTeacherDetails();
					request.setAttribute("allTeacherDetailsList", list);
					
					forward=MessageConstants.STAFF_EXCEL_FILE_UPLOAD;
					
				}

			} else {

				forward=MessageConstants.STAFF_EXCEL_FILE_UPLOAD;
			}

		} catch (Exception e) {
			System.out.println("exception Block::::: ");
			request.setAttribute("errorMessage","File is Corrupted or Empty.");
			forward=MessageConstants.STAFF_EXCEL_FILE_UPLOAD;
			e.printStackTrace();
			logger.error(e.getMessage(), e);
			return mapping.findForward(forward);

		}

		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStaffXSLAction : insertStageXSL : Ending");

		return mapping.findForward(forward);

	}

	
	  public ActionForward downloadStaffFileFormat(ActionMapping mapping, ActionForm
	  form,HttpServletRequest request, HttpServletResponse response) throws
	  Exception {
	  
	  logger.setLevel(Level.DEBUG); JLogger.log(0, JDate.getTimeString(new
	  Date()) + MessageConstants.START_POINT);
	  logger.info(JDate.getTimeString(new Date()) +
	  " Control in UploadStaffXSLAction : downloadStaffFileFormat : Starting");
	  
	  
	  System.out.println("downloadfileformat");
	  
	  
	  try {
	  
	  
	  String filePath = request.getRealPath("/") +"FIles/StaffFileUpload/StaffExcelFileFormat.xlsx"; 
	
	  
	  System.out.println("FILEPATH:::"+filePath);
	  
	  ServletOutputStream out  = response.getOutputStream(); FileInputStream in = new
	  FileInputStream(filePath); HttpSession ses = request.getSession();
	  response.setContentType("application/vnd.ms-excel");
	  response.addHeader("content-disposition", "attachment; filename=" +
	  "StaffExcelFileFormat.xlsx");
	  
	  int octet; while ((octet = in.read()) != -1) out.write(octet);
	  
	  in.close(); out.close();
	  
	  } catch (Exception e) {
		  e.printStackTrace(); 
		  logger.error(e.getMessage(), e); 
	  }
	  
	  
	  JLogger.log(0, JDate.getTimeString(new Date()) +
	  MessageConstants.END_POINT); logger.info(JDate.getTimeString(new Date())
	  + " Control in UploadStaffXSLAction : downloadStaffFileFormat : Ending");
	  
	  return null;
	  
	  }
	 

}
