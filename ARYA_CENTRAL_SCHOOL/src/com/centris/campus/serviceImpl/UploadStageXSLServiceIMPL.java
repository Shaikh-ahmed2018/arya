package com.centris.campus.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.json.JSONArray;

import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.daoImpl.UploadStageXLSDaoImpl;
import com.centris.campus.daoImpl.UploadStudentXLSDaoImpl;
import com.centris.campus.pojo.UploadStageXlsPOJO;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.service.UploadStageXSLservice;
import com.centris.campus.service.UploadStudentXSLservice;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.UploadStageXlsVO;
import com.centris.campus.vo.UploadStudentXlsVO;
import com.itextpdf.text.log.SysoLogger;


public class UploadStageXSLServiceIMPL implements UploadStageXSLservice {
	
	private static Logger logger = Logger.getLogger(UploadStageXSLServiceIMPL.class);


	public Set<UploadStageXlsVO> insertStageXSL(
			List<UploadStageXlsPOJO> list, String username, String duplicate) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLServiceImpl : insertStageXSL : Starting");
		
		Connection connection = null;
		
		List<UploadStageXlsPOJO> successlist=new ArrayList<UploadStageXlsPOJO>();
		UploadStageXLSDaoImpl daoImpl=new UploadStageXLSDaoImpl();
	
		int count = 0;
	
		Set<UploadStageXlsVO> failurelist = new LinkedHashSet<UploadStageXlsVO>();
	
		failurelist.clear();
		successlist.clear();

		try {
			System.out.println("Service IMPL is working for Excel file student");
			connection=JDBCConnection.getSeparateConnection();
			
			
			String string_regx="([a-zA-Z.]+\\s+)*[a-zA-Z.]+";
			String int_regex="^[0-9]*$";
			for (Iterator<UploadStageXlsPOJO> iterator = list.iterator(); iterator.hasNext();) {

				UploadStageXlsPOJO uploadStageXSLPOJO = (UploadStageXlsPOJO) iterator.next();
									
				UploadStageXlsVO uploadStageXSLVo = new UploadStageXlsVO();
			
				uploadStageXSLPOJO.setCreatedby(username);
			
				
				uploadStageXSLVo.setStage_name(uploadStageXSLPOJO.getStage_name());
				uploadStageXSLVo.setAmount(uploadStageXSLPOJO.getAmount());
				uploadStageXSLVo.setStage_description(uploadStageXSLPOJO.getStage_description());
				
				
				System.out.println("getStageName:::"+uploadStageXSLPOJO.getStage_name());

				if(uploadStageXSLPOJO.getStage_name()=="" || uploadStageXSLPOJO.getStage_name().equals("-") || uploadStageXSLPOJO.getStage_name()==null){
					System.out.println("Stage Name");
					uploadStageXSLVo.setReason("Stage Name Should Not Empty");
					failurelist.add(uploadStageXSLVo);
				}
				/*  else if(!(uploadStageXSLPOJO.getStage_name().matches(string_regx))){
					System.out.println("stage name  REGEX");

					uploadStageXSLVo.setReason("Stage Name Should be Alphabet");
					failurelist.add(uploadStageXSLVo);
				}
			   
			   else if(uploadStageXSLPOJO.getDuplicateInExcel()==0){
					System.out.println("Duplicate Value in Excel! ");

					uploadStageXSLVo.setReason("Duplicate in Excel");
					failurelist.add(uploadStageXSLVo);
				}*/
			   
			   else if((!uploadStageXSLPOJO.getAmount().matches(int_regex))){
					System.out.println("stage name  REGEX");

					uploadStageXSLVo.setReason("Amount Should be Number.");
					failurelist.add(uploadStageXSLVo);
				}else if(uploadStageXSLPOJO.getAmount().equals("")){
					uploadStageXSLVo.setReason("Amount Should Not be Empty.");
					failurelist.add(uploadStageXSLVo);
				}
				else if(uploadStageXSLPOJO.getAccyear().equals("")){
					uploadStageXSLVo.setReason("Amount Should Not be Empty.");
					failurelist.add(uploadStageXSLVo);
				}
				
				
			
			   
			   else if(daoImpl.checkAccyearName(uploadStageXSLPOJO.getAccyear(),connection).equalsIgnoreCase("No academicYear")){
					failurelist.add(uploadStageXSLVo);
					uploadStageXSLVo.setReason("Academic Year does not avaliable");
				}
			  				
			   else
			   {
				   uploadStageXSLPOJO.setAccyearid(daoImpl.checkAccyearName(uploadStageXSLPOJO.getAccyear(),connection));
				   System.out.println("Inside Else Block");
				   successlist.add(uploadStageXSLPOJO);
				   System.out.println("successlist.size()"+successlist.size());
				
				   JSONArray j=new JSONArray(successlist);
				   System.out.println("j:::"+j);
			   }
				
		}
		String success=daoImpl.insertStageXSL(successlist,connection);
		
		System.out.println("in serviceImpl: Duplicate Record  are: "+success);
		System.out.println("in service IMPL: faulurelist list size= "+failurelist.size());
		
		
		}catch (SQLException sqle) {

			sqle.printStackTrace();
			logger.error(sqle.getMessage(),sqle);

		} catch (Exception e) {

			e.printStackTrace();
			logger.error(e.getMessage(), e);

		}finally{
			
			try {
				
				if(connection!=null && (!connection.isClosed())){
					
					connection.close();
				}
			
			} catch (SQLException sqle) {
				   sqle.printStackTrace();
					logger.error(sqle.getMessage(), sqle);
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(e.getMessage(), e);

			}
		}

		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLServiceImpl : insertStageXSL : Ending");

		return failurelist;
	}

}
