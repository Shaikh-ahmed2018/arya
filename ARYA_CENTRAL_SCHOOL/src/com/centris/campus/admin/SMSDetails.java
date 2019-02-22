package com.centris.campus.admin;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.centris.campus.daoImpl.JDBCConnection;
import com.centris.campus.util.HelperClass;
import com.centris.campus.util.SmsUtilsConstants;
import com.centris.campus.vo.SMSAuditVO;

public class SMSDetails {

	
public void getHolidayDetails(String holidayCode,String holidayDate){
		
		try{
			
			System.out.println(" Inside getHolidayDetails");
			
		CallableStatement callableStatement= JDBCConnection.getConnection().prepareCall("{call approveHolidaySMS(?,?)}");
		callableStatement.setString(1, holidayCode);
		callableStatement.setString(2, holidayDate);
		ResultSet rs=callableStatement.executeQuery();
		
		System.out.println("Callabale Statement" +rs);
		
		SMSAuditVO smsAuditVO=null;
		while(rs.next()){
			
			
		String response=new SendSMS().sendSMS(rs.getString("student_contact_mobileno"),rs.getString("smsContent"));
		String messageId=response.substring(response.lastIndexOf("=")+1,response.length());
		String deliveryresponse=new SendSMS().getDeliveryStatus(messageId);
		 smsAuditVO =  new SMSAuditVO();
		/*smsAuditVO.setApprovedBy(rs.getString("MODIFIED_BY"));
		String modifyTime=rs.getString("MODIFIED_TIME");
		if(modifyTime==null){
			smsAuditVO.setApproveTime(HelperClass.getCurrentTimestamp()+"");
		}
		else{
			smsAuditVO.setApproveTime(rs.getString("MODIFIED_TIME"));
		}*/
		smsAuditVO.setCreatedBy(rs.getString("CREATED_BY"));
		smsAuditVO.setDeliveryStatus(deliveryresponse);
		smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
		smsAuditVO.setMobileNo(rs.getString("student_contact_mobileno"));
		//smsAuditVO.setSendBy(rs.getString("MODIFIED_BY"));
		smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
		smsAuditVO.setSmsContent(rs.getString("smsContent"));
		smsAuditVO.setSmsDate(rs.getString("smsDate"));
		smsAuditVO.setSmsResponse(response);
		if(deliveryresponse!=null && deliveryresponse.contains("DELIVERED"))
			smsAuditVO.setSmsStatus("Sent");
		else{
			smsAuditVO.setSmsStatus("Not Sent");
		}
		smsAuditVO.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
		String templateCode=rs.getString("TemplateCode");
		if(templateCode==null){
			templateCode="TEM1";
		}
		smsAuditVO.setTemplateCode(templateCode);
		smsAuditVO.setServiceCode(holidayCode);
		
		
		int p=new SendSMS().insertSMSDetails(smsAuditVO);
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public String getMeetingDetails(String meetingCode,String meetingDate){
		try{
		CallableStatement callableStatement= (CallableStatement)JDBCConnection.getConnection().prepareCall("{call approveMeetingSMS(?,?)}");
		callableStatement.setString(1, meetingCode);
		callableStatement.setString(2, meetingDate);
		ResultSet rs=callableStatement.executeQuery();
		SMSAuditVO smsAuditVO =null;
		while(rs.next()){
		String response=new SendSMS().sendSMS(rs.getString("student_contact_mobileno"),rs.getString("smsContent"));
		String messageId=response.substring(response.lastIndexOf("=")+1,response.length());
		String deliveryresponse=new SendSMS().getDeliveryStatus(messageId);
		 smsAuditVO =  new SMSAuditVO();
			smsAuditVO.setApprovedBy(rs.getString("MODIFIED_BY"));
			String modifyTime=rs.getString("MODIFIED_TIME");
			if(modifyTime==null){
				smsAuditVO.setApproveTime(HelperClass.getCurrentTimestamp()+"");
			}
			else{
				smsAuditVO.setApproveTime(rs.getString("MODIFIED_TIME"));
			}
			smsAuditVO.setCreatedBy(rs.getString("CREATED_BY"));
			smsAuditVO.setDeliveryStatus(deliveryresponse);
			smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
			smsAuditVO.setMobileNo(rs.getString("student_contact_mobileno"));
			smsAuditVO.setSendBy(rs.getString("MODIFIED_BY"));
			smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
			smsAuditVO.setSmsContent(rs.getString("smsContent"));
			smsAuditVO.setSmsDate(rs.getString("smsDate"));
			smsAuditVO.setSmsResponse(response);
			if(deliveryresponse!=null && deliveryresponse.contains("DELIVRD"))
				smsAuditVO.setSmsStatus("Sent");
			else{
				smsAuditVO.setSmsStatus("Not Sent");
			}
			smsAuditVO.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
			String templateCode=rs.getString("TemplateCode");
			if(templateCode==null){
				templateCode="TEM1";
			}
			smsAuditVO.setTemplateCode(templateCode);
			smsAuditVO.setServiceCode(meetingCode);
		int p=new SendSMS().insertSMSDetails(smsAuditVO);
		}
	}
	catch(Exception e){
		e.printStackTrace();
	}
		return "Meeting SMS";
	}
	public void getEventDetails(String eventCode,String eventDate){
		
		try{
		CallableStatement callableStatement= JDBCConnection.getConnection().prepareCall("{call approveEventSMS(?,?)}");
		callableStatement.setString(1, eventCode);
		callableStatement.setString(2, eventDate);
		ResultSet rs=callableStatement.executeQuery();
		SMSAuditVO smsAuditVO=null;
		while(rs.next()){
		String response=new SendSMS().sendSMS(rs.getString("student_contact_mobileno"),rs.getString("smsContent"));
		String messageId=response.substring(response.lastIndexOf("=")+1,response.length());
		String deliveryresponse=new SendSMS().getDeliveryStatus(messageId);
		 smsAuditVO =  new SMSAuditVO();
			smsAuditVO.setApprovedBy(rs.getString("MODIFIED_BY"));
			String modifyTime=rs.getString("MODIFIED_TIME");
			if(modifyTime==null){
				smsAuditVO.setApproveTime(HelperClass.getCurrentTimestamp()+"");
			}
			else{
				smsAuditVO.setApproveTime(rs.getString("MODIFIED_TIME"));
			}
			smsAuditVO.setCreatedBy(rs.getString("CREATED_BY"));
			smsAuditVO.setDeliveryStatus(deliveryresponse);
			smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
			smsAuditVO.setMobileNo(rs.getString("student_contact_mobileno"));
			smsAuditVO.setSendBy(rs.getString("MODIFIED_BY"));
			smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
			smsAuditVO.setSmsContent(rs.getString("smsContent"));
			smsAuditVO.setSmsDate(rs.getString("smsDate"));
			smsAuditVO.setSmsResponse(response);
			if(deliveryresponse!=null && deliveryresponse.contains("DELIVRD"))
				smsAuditVO.setSmsStatus("Sent");
			else{
				smsAuditVO.setSmsStatus("Not Sent");
			}
			smsAuditVO.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
			String templateCode=rs.getString("TemplateCode");
			if(templateCode==null){
				templateCode="TEM1";
			}
			smsAuditVO.setTemplateCode(templateCode);
			smsAuditVO.setServiceCode(eventCode);
		int p=new SendSMS().insertSMSDetails(smsAuditVO);
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void getLateComingStudentDetails(String lateCode,String lateDate){
		
		try{
		CallableStatement callableStatement= JDBCConnection.getConnection().prepareCall("{call approveLateComingStudentSMS(?,?)}");
		callableStatement.setString(1, lateCode);
		callableStatement.setString(2, lateDate);
		ResultSet rs=callableStatement.executeQuery();
		SMSAuditVO smsAuditVO=null;
		while(rs.next()){
		String response=new SendSMS().sendSMS(rs.getString("student_contact_mobileno"),rs.getString("smsContent"));
		String messageId=response.substring(response.lastIndexOf("=")+1,response.length());
		String deliveryresponse=new SendSMS().getDeliveryStatus(messageId);
		 smsAuditVO =  new SMSAuditVO();
			smsAuditVO.setApprovedBy(rs.getString("MODIFIED_BY"));
			String modifyTime=rs.getString("MODIFIED_TIME");
			if(modifyTime==null){
				smsAuditVO.setApproveTime(HelperClass.getCurrentTimestamp()+"");
			}
			else{
				smsAuditVO.setApproveTime(rs.getString("MODIFIED_TIME"));
			}
			smsAuditVO.setCreatedBy(rs.getString("CREATED_BY"));
			smsAuditVO.setDeliveryStatus(deliveryresponse);
			smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
			smsAuditVO.setMobileNo(rs.getString("student_contact_mobileno"));
			smsAuditVO.setSendBy(rs.getString("MODIFIED_BY"));
			smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
			smsAuditVO.setSmsContent(rs.getString("smsContent"));
			smsAuditVO.setSmsDate(rs.getString("smsDate"));
			smsAuditVO.setSmsResponse(response);
			if(deliveryresponse!=null && deliveryresponse.contains("DELIVRD"))
				smsAuditVO.setSmsStatus("Sent");
			else{
				smsAuditVO.setSmsStatus("Not Sent");
			}
			smsAuditVO.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
			String templateCode=rs.getString("TemplateCode");
			if(templateCode==null){
				templateCode="TEM1";
			}
			smsAuditVO.setTemplateCode(templateCode);
			smsAuditVO.setServiceCode(lateCode);
		int p=new SendSMS().insertSMSDetails(smsAuditVO);
		}
	}
	catch(Exception e){
		e.printStackTrace();
	}
		
	}
	
	public void getUniformDetails(String uniformCode,String uniformDate){
		
		try{
		
		/*CallableStatement callableStatement= JDBCConnection.getConnection().prepareCall("{call approveUniformSMS(?,?)}");
		callableStatement.setString(1, uniformCode);
		callableStatement.setString(2, uniformDate);
		ResultSet rs=callableStatement.executeQuery();
		SMSAuditVO smsAuditVO=null;
		while(rs.next()){
		String response=new SendSMS().sendSMS(rs.getString("student_contact_mobileno"),rs.getString("smsContent"));
		String messageId=response.substring(response.lastIndexOf("=")+1,response.length());
		String deliveryresponse=new SendSMS().getDeliveryStatus(messageId);
		 smsAuditVO =  new SMSAuditVO();
			smsAuditVO.setCreatedBy(rs.getString("CREATED_BY"));
			smsAuditVO.setDeliveryStatus(deliveryresponse);
			smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
			smsAuditVO.setMobileNo(rs.getString("student_contact_mobileno"));
			smsAuditVO.setSendBy(rs.getString("MODIFIED_BY"));
			smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
			smsAuditVO.setSmsContent(rs.getString("smsContent"));
			smsAuditVO.setSmsDate(rs.getString("smsDate"));
			smsAuditVO.setSmsResponse(response);
			if(deliveryresponse!=null && deliveryresponse.contains("DELIVRD"))
				smsAuditVO.setSmsStatus("Sent");
			else{
				smsAuditVO.setSmsStatus("Not Sent");
			}
			smsAuditVO.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
			String templateCode=rs.getString("TemplateCode");
			if(templateCode==null){
				templateCode="TEM1";
			}
			smsAuditVO.setTemplateCode(templateCode);
			smsAuditVO.setServiceCode(uniformCode);
		int p=new SendSMS().insertSMSDetails(smsAuditVO);
		}*/
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void getAbsentDetails(String absentCode,String absentDate){
		
		try{
			
			System.out.println("Inside getAbsentDetails");
			
			System.out.println("absentCode" + absentCode + "absentDate " +absentDate );
			SMSAuditVO smsAuditVO=null;
		CallableStatement callableStatement= JDBCConnection.getConnection().prepareCall("{call approveAbsentSMS(?,?)}");
		callableStatement.setString(1, absentCode.trim());
		callableStatement.setString(2, absentDate.trim());
		System.out.println("cccccccccc" +callableStatement.toString());
		
		ResultSet rs=callableStatement.executeQuery();
		
		System.out.println("rs.next()" +rs.next());
		
		while(rs.next()){
			
			System.out.println("Inside While");
			
		String response=new SendSMS().sendSMS(rs.getString("student_contact_mobileno"),rs.getString("smsContent"));
		String messageId=response.substring(response.lastIndexOf("=")+1,response.length());
		String deliveryresponse=new SendSMS().getDeliveryStatus(messageId);
		smsAuditVO =  new SMSAuditVO();
		/*smsAuditVO.setApprovedBy(rs.getString("MODIFIED_BY"));
		String modifyTime=rs.getString("MODIFIED_TIME");
		if(modifyTime==null){
			smsAuditVO.setApproveTime(HelperClass.getCurrentTimestamp()+"");
		}
		else{
			smsAuditVO.setApproveTime(rs.getString("MODIFIED_TIME"));
		}*/
		smsAuditVO.setCreatedBy(rs.getString("CREATED_BY"));
		smsAuditVO.setDeliveryStatus(deliveryresponse);
		smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
		smsAuditVO.setMobileNo(rs.getString("student_contact_mobileno"));
		smsAuditVO.setSendBy(rs.getString("CREATED_BY"));
		smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
		smsAuditVO.setSmsContent(rs.getString("smsContent"));
		smsAuditVO.setSmsDate(rs.getString("smsDate"));
		smsAuditVO.setSmsResponse(response);
		if(deliveryresponse!=null && deliveryresponse.contains("DELIVRD"))
			smsAuditVO.setSmsStatus("Sent");
		else{
			smsAuditVO.setSmsStatus("Not Sent");
		}
		smsAuditVO.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
		String templateCode=rs.getString("TemplateCode");
		if(templateCode==null){
			templateCode="TEM1";
		}
		smsAuditVO.setTemplateCode(templateCode);
		smsAuditVO.setServiceCode(absentCode);
		int p=new SendSMS().insertSMSDetails(smsAuditVO);
		}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void getBirthdayWishesDetails(String birthdayCode,String birthdayDate){
		
		try{
		CallableStatement callableStatement= JDBCConnection.getConnection().prepareCall("{call approveBirthdayWishesSMS(?,?)}");
		callableStatement.setString(1, birthdayCode);
		callableStatement.setString(2, birthdayDate);
		ResultSet rs=callableStatement.executeQuery();
		SMSAuditVO smsAuditVO=null;
		while(rs.next()){
			String response=new SendSMS().sendSMS(rs.getString("student_contact_mobileno"),rs.getString("smsContent"));
			String messageId=response.substring(response.lastIndexOf("=")+1,response.length());
			String deliveryresponse=new SendSMS().getDeliveryStatus(messageId);
			smsAuditVO =  new SMSAuditVO();
			smsAuditVO.setApprovedBy(rs.getString("MODIFIED_BY"));
			String modifyTime=rs.getString("MODIFIED_TIME");
			if(modifyTime==null){
				smsAuditVO.setApproveTime(HelperClass.getCurrentTimestamp()+"");
			}
			else{
				smsAuditVO.setApproveTime(rs.getString("MODIFIED_TIME"));
			}
			smsAuditVO.setCreatedBy(rs.getString("CREATED_BY"));
			smsAuditVO.setDeliveryStatus(deliveryresponse);
			smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
			smsAuditVO.setMobileNo(rs.getString("student_contact_mobileno"));
			smsAuditVO.setSendBy(rs.getString("MODIFIED_BY"));
			smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
			smsAuditVO.setSmsContent(rs.getString("smsContent"));
			smsAuditVO.setSmsDate(rs.getString("smsDate"));
			smsAuditVO.setSmsResponse(response);
			if(deliveryresponse!=null && deliveryresponse.contains("DELIVRD"))
				smsAuditVO.setSmsStatus("Sent");
			else{
				smsAuditVO.setSmsStatus("Not Sent");
			}
			smsAuditVO.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
			String templateCode=rs.getString("TemplateCode");
			if(templateCode==null){
				templateCode="TEM1";
			}
			smsAuditVO.setTemplateCode(templateCode);
			smsAuditVO.setServiceCode(birthdayCode);
			int p=new SendSMS().insertSMSDetails(smsAuditVO);
		}
	}
	catch(Exception e){
		e.printStackTrace();
	}
		
	}
	public String getFeeDetails(String declarationCode,String declarationDate){
		try{
			
			System.out.println("Inside Fee Details ");
			
		PreparedStatement callableStatement= JDBCConnection.getConnection().prepareStatement(SmsUtilsConstants.SEND_FEE_DETAILS);
		callableStatement.setString(1, declarationCode);
		callableStatement.setString(2, declarationDate);
		ResultSet rs=callableStatement.executeQuery();
		
		System.out.println("SMS FEEE" +callableStatement);
		
		SMSAuditVO smsAuditVO =null;
		while(rs.next()){
		String response=new SendSMS().sendSMS(rs.getString("student_contact_mobileno"),rs.getString("smsContent"));
		String messageId=response.substring(response.lastIndexOf("=")+1,response.length());
		String deliveryresponse=new SendSMS().getDeliveryStatus(messageId);
		 smsAuditVO =  new SMSAuditVO();
			
			smsAuditVO.setCreatedBy(rs.getString("CREATED_BY"));
			smsAuditVO.setDeliveryStatus(deliveryresponse);
			smsAuditVO.setDeliveryTime(HelperClass.getCurrentTimestamp()+"");
			smsAuditVO.setMobileNo(rs.getString("student_contact_mobileno"));
			smsAuditVO.setSentTime(HelperClass.getCurrentTimestamp()+"");
			smsAuditVO.setSmsContent(rs.getString("smsContent"));
			smsAuditVO.setSmsDate(rs.getString("smsDate"));
			smsAuditVO.setSmsResponse(response);
			if(deliveryresponse!=null && deliveryresponse.contains("DELIVRD"))
				smsAuditVO.setSmsStatus("Sent");
			else{
				smsAuditVO.setSmsStatus("Not Sent");
			}
			smsAuditVO.setStudentAdmissionNo(rs.getString("student_admissionno_var"));
			String templateCode="TEM8";
			
			smsAuditVO.setTemplateCode(templateCode);
			smsAuditVO.setServiceCode(declarationCode);
		int p=new SendSMS().insertSMSDetails(smsAuditVO);
		}
	}
	catch(Exception e){
		e.printStackTrace();
	}
		return "Declaration SMS";
	}
	
	
}
