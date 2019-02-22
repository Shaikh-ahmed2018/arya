package com.centris.campus.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;


import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.delegate.ParentExamdetailsBD;
import com.centris.campus.forms.ParentFeedbackform;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.ParentFeedbackVo;

public class ParentFeebackEntryAction extends DispatchAction{
	
	private static final Logger logger = Logger.getLogger(ParentFeebackEntryAction.class);
	
	
	public ActionForward feedbackentry(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feedbackentry Starting");
		
		
		
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : feedbackentry Ending");
		
		
		return mapping.findForward(MessageConstants.feedbackentry);
		
	}

	
	
	public ActionForward savefeedback(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : savefeedback Starting");
		
		
	
		String path = null;
		String extension = null;
		File fileURL = null;
		FileOutputStream fos = null;
		
		try {
			
			ParentFeedbackform fbform =(ParentFeedbackform)form; 
			ParentFeedbackVo fbvo = new ParentFeedbackVo();
	        String createUser = "Parent";
			
			String accyear = HelperClass.getAcademicYear();
		
			
			String fbid = IDGenerator.getPrimaryKeyID("campus_feedback");
			fbform.setFeedbackid(fbid);
			fbform.setCreateUser(createUser);
			
			
				
				FormFile formFile = fbform.getAddfile();
				
				
				
				if(formFile.getFileSize()>0){
					
					
					path = getServlet().getServletContext().getRealPath("/")+ "FIles\\FEEDBACK";
					File files = new File(path);
					if (!files.exists()) {
						if (files.mkdirs()) {
						}
					}
					path = files.getAbsolutePath();
					
					int i = formFile.getFileName().lastIndexOf('.');
					if (i > 0) {
						extension = formFile.getFileName().substring(i + 1);
					}
					
					String fileName = HelperClass.getCurrentSqlDate() + "_"	+ formFile ;
					fileURL = new File(path, fileName);
		
					fos = new FileOutputStream(fileURL);
					fos.write(formFile.getFileData());
		
					String file1 = fileURL.getPath();
		
					file1 = file1.substring(file1.indexOf("FIles\\"));
		
					if (!fileURL.exists()) {
						FileOutputStream fileOutStream = new FileOutputStream(fileURL);
		
						fileURL.mkdir();
						fileOutStream.write(formFile.getFileData());
		
						fileOutStream.flush();
		
						fileOutStream.close();
					}
					
					fbvo.setAddfile(file1);
					
				}
				
				else{
					
					fbvo.setAddfile(fbform.getAddfile1());
					
				}
				
			
			
			fbvo.setFeedbackid(fbform.getFeedbackid());
			fbvo.setFeedbackto(fbform.getFeedbackto());
			fbvo.setDescription(fbform.getDescription());
			fbvo.setCreateUser(fbform.getCreateUser());
			
				
			String result =  new ParentExamdetailsBD()
			.saveFeedBackDetails(fbvo);
			
			
			if(MessageConstants.FEEDTRUE.equalsIgnoreCase(result)){
				
				request.setAttribute("successMessage", MessageConstants.INSERT_FEEDBACK_SUCCESS);
			}else{
				
				request.setAttribute("errorMessage", MessageConstants.INSERT_FEEDBACK_FAILURE);

			}
			
			
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AdminMenuAction : savefeedback Ending");
		
		
		
		return mapping.findForward(MessageConstants.feedbackentry);
		
	}
	
	
	
	
	
	
}
