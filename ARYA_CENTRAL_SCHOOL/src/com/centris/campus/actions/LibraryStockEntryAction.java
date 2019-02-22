package com.centris.campus.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.json.JSONObject;

import com.centris.campus.delegate.LibraryDelegate;
import com.centris.campus.forms.LibraryStockEntryDetailsForm;
import com.centris.campus.forms.StudentRegistrationForm;
import com.centris.campus.pojo.LibraryLocationPojo;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.LibraryMessageConstants;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.CategoryTypeVO;
import com.centris.campus.vo.LibraryStockEntryVO;
import com.centris.campus.vo.UserDetailVO;

public class LibraryStockEntryAction extends DispatchAction {
	private static final Logger logger = Logger
			.getLogger( LibraryStockEntryAction.class);
	
	
	public ActionForward saveStockEnteryDetails(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryStockEntryAction :saveStockEnteryDetails Starting");
		
		
		String keyValues = null;
		FormFile imagePath = null;
		String realPath = "";
		FileOutputStream outputStream = null;
		String imagepath;
		String fileName = " ";
		FormFile formFile = null;
		String path = null;
		
		try{
			  String academicYearFace= (String) request.getSession(false).getAttribute("current_academicYear");
		    
			String msg="Stock Entry Details";
			request.setAttribute("msg", msg);
			
			UserDetailVO userDetailVO = (UserDetailVO) request.getSession(false).getAttribute("UserDetails");
			String userCode = userDetailVO.getUserId();
			
			LibraryStockEntryDetailsForm libform = (LibraryStockEntryDetailsForm)form;
			String date = libform.getRegdate();
			System.out.println("date is " +date);
		
			libform.setCreatedby(userCode);
			
			LibraryDelegate obj = new LibraryDelegate();
			List<CategoryTypeVO> list = new ArrayList<CategoryTypeVO>();
			list = obj.getCategoryDetails();
			request.setAttribute("categoryDetails", list);
			
			List<LibraryLocationPojo> loclist = new ArrayList<LibraryLocationPojo>();
			/*loclist = obj.getLibLocationsDetails();*/
			request.setAttribute("librarylocationsDetails", loclist);
			
			
			List<LibraryStockEntryVO> publist = new LibraryDelegate().getPublisherSettingList();
			request.setAttribute("publicationlist", publist);
			
			
			
			
			    
			try{
				
				if(date != null){
					File file=new File(request.getRealPath("/")+"FIles/StockEntryImages/"+academicYearFace);
					  if(!file.exists()){
						  file.mkdirs();
					  }
					  
					 /* imagePath =libform.getBookimage();
					  
					  System.out.println("imagePath is " +request.getParameter("bookimage"));
						
					  if( imagePath!=null){
						  fileName =imagePath.getFileName();
					  }
			           
					  System.out.println(fileName);
					  
						int dot = imagePath.getFileName().lastIndexOf('.');
					  if(dot>=0){
						extension=imagePath.getFileName().substring(dot+1);
					
					}
					if(fileName.length()<2&&fileName!=null){
						libform.setBookPhoto(getServlet().getServletContext().getRealPath("/")+"Files/StockEntryImages/"+"noImage.png");
						
					}
					
					else if(extension.equalsIgnoreCase("jpg")){
						imagepath = getServlet().getServletContext().getRealPath("")+ "\\FIles\\StockEntryImages"+ "/"+academicYearFace+"/"+libform.getAccessionNo()+ "." + extension;
						realPath = imagepath.substring(imagepath.indexOf("FIles\\"));
						outputStream = new FileOutputStream(new File(imagepath));
						outputStream.write(imagePath.getFileData());
						}else if(extension.equalsIgnoreCase("jpeg")){
						imagepath = getServlet().getServletContext().getRealPath("")+ "\\FIles\\StockEntryImages"+ "/"+academicYearFace+"/"+libform.getAccessionNo()+ "." + extension;
						realPath = imagepath.substring(imagepath.indexOf("FIles\\"));
						outputStream = new FileOutputStream(new File(imagepath));
						outputStream.write(imagePath.getFileData());
					}else if(extension.equalsIgnoreCase("png")){
						imagepath = getServlet().getServletContext().getRealPath("")+ "\\FIles\\StockEntryImages"+ "/"+academicYearFace+"/"+libform.getAccessionNo()+ "." + extension;
						realPath = imagepath.substring(imagepath.indexOf("FIles\\"));
						outputStream = new FileOutputStream(new File(imagepath));
						outputStream.write(imagePath.getFileData());
					}
					
					libform.setBookPhoto(realPath);*/
					  String accno=libform.getAccessionNo();
					  System.out.println("libform.getBookimage()"+libform.getBookimage());
					  if (libform.getBookimage() != null) {

						  String extension=null;

						  int i = libform.getBookimage().getFileName().lastIndexOf('.');
						  if (i >= 0) {

							  extension = libform.getBookimage().getFileName().substring(i+1);
						  }

						  String image_path = "FIles/StockEntryImages"+ "/"+accno+ "." + extension;

						  String filePath = request.getRealPath("/") + "FIles/StockEntryImages"+ "/"+accno+ "." + extension;

						  if (libform.getBookimage().getFileSize() > 0) {
							  byte[] btDataFile = libform.getBookimage().getFileData();
							  File of = new File(filePath);
							  FileOutputStream osf = new FileOutputStream(of);
							  osf.write(btDataFile);
							  osf.flush();
						  } else {

							  image_path = libform.getPreviousImage();
						  }

						  libform.setBookPhoto(image_path);


					  }else{
					
						libform.setBookPhoto(libform.getPreviousImage());
					} 
					  
			    	String result = new LibraryDelegate().saveStockEnteryDetails(libform);
			    	String msg1="";
			    	if(result == "addsuccess"){
			    		msg1="Updating Record Progressing...";
			    		request.setAttribute("result", msg1);
			    	}
			    	else if(result == "success"){
			    		msg1="Adding Record Progressing...";
			    		request.setAttribute("result", msg1);
			    	}else if(result == "fail"){
			    		msg1 = "Adding Record Failed";
			    		request.setAttribute("fail", msg1);
			    	}
			    	else if(result == "updatefail"){
			    		msg1 = "Record Updation Failed";
			    		request.setAttribute("fail", msg1);
			    	}
				}

			}
			catch(Exception e){
				e.printStackTrace();
			}
			finally {
				
				if (outputStream != null) {

					outputStream.close();
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryStockEntryAction : saveStockEnteryDetails Ending");
		
		return mapping.findForward(LibraryMessageConstants.StockEntryDetails);
	}
	
	
	
	
	
	
	
	public ActionForward validateaccessionno(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in LibraryStockEntryAction :validateStockEnteryDetailsStarting");
		try{
			
			 String accno =request.getParameter("accession");
			String result = new LibraryDelegate().validateStockEnteryDetails(accno);
			
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
				+ " Control in LibraryStockEntryAction : validateStockEnteryDetails Ending");
		
		return null;
	}
	




}
