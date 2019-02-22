package com.centris.campus.actions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.JDBCConnectionNoLoggers;
import com.centris.campus.util.JPropertyReader;
import com.centris.campus.util.SmsUtilsConstants;

public class SendBirthdayWishesSMS {

	
	public void SendBday() throws Exception {
	
		
			System.out.println("SendBirthdayWishesSMS Action ");
				
			System.out.println("Inside Send Birthday DAOIMPLssss");
			
			PreparedStatement pstmt = null;
			String result_Status = "";
			int result = 0;
			Connection conn = null;
			PreparedStatement pstmt1 = null;
			String smsContent = null;
			
			ResultSet rs1 = null,rs=null;
			try {
				
				System.out.println("Inside Send Birthday DAOIMPL");
				
				conn = JDBCConnectionNoLoggers.getSeparateConnection();
				String todayDate = getCurrentSqlDate().toString().substring(5,10);
				System.out.println(todayDate);
				
				
				pstmt = conn.prepareStatement(SmsUtilsConstants.CHECK_BDAY_DATE);
				pstmt.setString(1, "%" +todayDate + "%");
				
			
				rs = pstmt.executeQuery();
				
				while(rs.next())
				{
					result = rs.getInt(1);
					
					if(result>0)
					{
						System.out.println("Call for BDAY Wishes");
						
						pstmt1 = conn.prepareStatement(SmsUtilsConstants.GET_SMS_DETAILS_FOR_BDAY);
						pstmt1.setString(1, "%" + todayDate + "%");
						
						rs1= pstmt1.executeQuery();
						
						while(rs1.next())
						{
							
							String str = IDGenerator.getPrimaryKeyID("birthday_sms");
							
							smsContent = "The sweetest greetings to the most adorable child.May your special day be filled with the moments of endless joy and fun!!! Happy Birthday " + rs1.getString("name")+ " ";
							
							pstmt = conn.prepareStatement(SmsUtilsConstants.SAVE_BIRTHDAY);
							pstmt.setString(1, str);
							pstmt.setDate(2, getCurrentSqlDate());
							pstmt.setString(3, rs1.getString("classdetail_id_int"));
							pstmt.setString(4, rs1.getString("classsection_id_int"));
							pstmt.setString(5, rs1.getString("student_id_int"));
							pstmt.setString(6, smsContent);
							pstmt.setString(7, "USR1");
							result = pstmt.executeUpdate();		
							
							System.out.println("BDAY" +pstmt);
					
							if (result == 1) {
								result_Status = "Message sent successfully";
								String sms = sendSMS(rs1.getString("student_contact_mobileno"), smsContent);
								
							} else {
								result_Status = "Message sending failed";
								
							}
					
						}
						
					}
					else
					{
						System.out.println("Doesn't Exists");
					}
				}
				
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	public static java.sql.Date getCurrentSqlDate() {
		java.util.Date sd = new java.util.Date();
		java.sql.Date currdate = null;
		try {
			currdate = new java.sql.Date(sd.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currdate;
	}
	
	
	public synchronized String sendSMS(String phoneNo, String content) {

		
		String urlResponse = "";
		try {
			
			System.out.println( " Control in SendSMS: sendSMS Starting");
			
			String smsURL = JPropertyReader.getProperty("SMS_URL").trim();
			String smsMobileParameter = JPropertyReader.getProperty(
					"SMS_MOBILE_PARAMETER").trim();
			String smsMessageParameter = JPropertyReader.getProperty(
					"SMS_MESSAGE_CONTENT_PARAMETER").trim();
			String smsString = smsURL + "&" + smsMobileParameter + "="
					+ phoneNo + "&" + smsMessageParameter + "="
					+ java.net.URLEncoder.encode(content, "UTF-8");
			System.out.println();
			System.out.println(" SENDING SMS ");
			System.out.println();
			System.out.println("SMS String::" + smsString);
			System.out.println("SMS Content::" + content);
			System.out.println();
			System.out.println();
			System.out.println();
			Thread.sleep(1000);
			URL url = new URL(smsString);
			URLConnection urlConnection = url.openConnection();
			HttpURLConnection connection = null;
			if (urlConnection instanceof HttpURLConnection) {
				connection = (HttpURLConnection) urlConnection;
			} else {
				return "";
			}

			int code = connection.getResponseCode();
			if (code == 200) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));

				String current;
				while ((current = in.readLine()) != null) {
					urlResponse += current;
				}
				System.out.println("deepthi bharath"+urlResponse);

				in.close();
			} else {
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return urlResponse;
	}
	
	
}
