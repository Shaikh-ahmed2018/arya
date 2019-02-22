package com.centris.campus.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.daoImpl.AbsentSMSDaoImpl;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.pojo.AbsentsSMSPojo;
import com.centris.campus.service.AbsentSMSService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;

public class AbsentSMSServiceImpl implements AbsentSMSService{
	
	private static final Logger logger = Logger.getLogger(AbsentSMSServiceImpl.class);

	public ArrayList<AbsentsSMSPojo> absentlist() {
		
		return new AbsentSMSDaoImpl().absentlist();
	}

	public String storeAbsentSms(AbsentsSMSPojo absentpojo) {
	
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.START_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AbsentSMSServiceImpl: storeAbsentSms Starting");

		
		String status=null;
		String absentcode=null;
		int count=0;
		
		try {
		
			absentcode=IDGenerator.getPrimaryKeyID("sms_absent_details");
					
					absentpojo.setIssection(0);
		 			absentpojo.setIsstudent(1);
					absentpojo.setAbsentcode(absentcode);
					new AbsentSMSDaoImpl().storeAbsentDetails(absentpojo);
					count=new AbsentSMSDaoImpl().storeAbsentStudent(absentpojo);
					count=new AbsentSMSDaoImpl().storeAbsentSections(absentpojo);
			
			
		
		
		if(count==1){
			
			status="Absent Message Sent Successfully";
			
		}else{
			
			status="Absent Message Sending Failed";
		}
	
		
} catch (SQLException e) {
			e.printStackTrace();
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		
		 logger.setLevel(Level.DEBUG);
			JLogger.log(0, JDate.getTimeString(new Date())
					+ MessageConstants.END_POINT);
			logger.info(JDate.getTimeString(new Date())
					+ " Control in AbsentSMSServiceImpl: storeAbsentSms Ending");
		
		return status;
	}

	public boolean validateAbsentSms(String date, String smstext) {
		
		return new AbsentSMSDaoImpl().validateAbsentSms(date,smstext);
	}

}
