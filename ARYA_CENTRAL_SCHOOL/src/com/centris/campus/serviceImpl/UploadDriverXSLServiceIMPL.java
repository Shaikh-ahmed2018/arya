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
import com.centris.campus.daoImpl.UploadDriverXLSDaoImpl;
import com.centris.campus.daoImpl.UploadStageXLSDaoImpl;
import com.centris.campus.daoImpl.UploadStudentXLSDaoImpl;
import com.centris.campus.pojo.UploadDriverXlsPOJO;
import com.centris.campus.pojo.UploadStudentXlsPOJO;
import com.centris.campus.service.UploadDriverXSLservice;
import com.centris.campus.service.UploadStageXSLservice;
import com.centris.campus.service.UploadStudentXSLservice;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.UploadDriverXlsVO;
import com.centris.campus.vo.UploadStudentXlsVO;
import com.itextpdf.text.log.SysoLogger;


public class UploadDriverXSLServiceIMPL implements UploadDriverXSLservice {
	
	private static Logger logger = Logger.getLogger(UploadDriverXSLServiceIMPL.class);


	public Set<UploadDriverXlsVO> insertDriverXSL(
			List<UploadDriverXlsPOJO> list, String username, String duplicate) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in UploadStageXSLServiceImpl : insertStageXSL : Starting");
		
		Connection connection = null;
		
		List<UploadDriverXlsPOJO> successlist=new ArrayList<UploadDriverXlsPOJO>();
		UploadDriverXLSDaoImpl daoImpl=new UploadDriverXLSDaoImpl();
	
		int count = 0;
	
		Set<UploadDriverXlsVO> failurelist = new LinkedHashSet<UploadDriverXlsVO>();
	
		failurelist.clear();
		successlist.clear();

		try {
			System.out.println("Service IMPL is working for Excel file Driver");
			connection=JDBCConnection.getSeparateConnection();
			
			
			String string_regx="([a-zA-Z.]+\\s+)*[a-zA-Z.]+";
			String int_regex="^[0-9]*$";
			for (Iterator<UploadDriverXlsPOJO> iterator = list.iterator(); iterator.hasNext();) {

				UploadDriverXlsPOJO uploadDriverXSLPOJO = (UploadDriverXlsPOJO) iterator.next();
									
				UploadDriverXlsVO uploadDriverXSLVo = new UploadDriverXlsVO();
			
				uploadDriverXSLPOJO.setCreatedby(username);
			
				
				uploadDriverXSLVo.setDriverName(uploadDriverXSLPOJO.getDriverName());
				uploadDriverXSLVo.setMobile(uploadDriverXSLPOJO.getMobile());
				uploadDriverXSLVo.setDrivingLicenseNo(uploadDriverXSLPOJO.getDrivingLicenseNo());
				uploadDriverXSLVo.setLicenseToDrive(uploadDriverXSLPOJO.getLicenseToDrive());
				
				System.out.println("getDriverName:::"+uploadDriverXSLPOJO.getDriverName());

				if(uploadDriverXSLPOJO.getDriverName()=="" || uploadDriverXSLPOJO.getDriverName().equals("-") || uploadDriverXSLPOJO.getDriverName()==null){
					System.out.println("Driver Name");
					uploadDriverXSLVo.setReason("Driver Name Should Not Empty");
					failurelist.add(uploadDriverXSLVo);
				}else if(uploadDriverXSLPOJO.getDrivingLicenseNo()=="" || uploadDriverXSLPOJO.getDrivingLicenseNo().equals("-") || uploadDriverXSLPOJO.getDrivingLicenseNo()==null){
					System.out.println("Driver Licence");
					uploadDriverXSLVo.setReason("Driver License No Should Not Empty");
					failurelist.add(uploadDriverXSLVo);
				}
				else{
			
				   System.out.println("Inside Else Block");
				   successlist.add(uploadDriverXSLPOJO);
				   System.out.println("successlist.size()"+successlist.size());
				
				   JSONArray j=new JSONArray(successlist);
				   System.out.println("j:::"+j);
			   }
				
		}
			
		String success=daoImpl.insertDriverXSL(successlist,connection);
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
