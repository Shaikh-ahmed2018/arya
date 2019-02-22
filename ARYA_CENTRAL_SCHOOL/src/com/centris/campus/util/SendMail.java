package com.centris.campus.util;

//this is a file used to send mails
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.ResourceBundle;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import com.centris.campus.admin.EmailContent;
import com.centris.campus.daoImpl.JDBCConnection;

public class SendMail {

	String FROM_ADDRESS = "donotreply@centrisinfotech.com";
	String SENDER_PASSWORD = "DoN%rP)987";

	static ResourceBundle res = ResourceBundle
			.getBundle("com/centris/campus/properties/CAMPUS");

	private static String ClientURL = res.getString("ClientURL");

	/*
	 * String FROM_ADDRESS = "ranjith.sivan@spectrumconsultants.com"; String
	 * SENDER_PASSWORD = "ranjith1234";
	 */

	public synchronized String sendMail(String toAddress,
			InternetAddress[] ccList, String subject, String Bodymess)
			throws MessagingException, AddressException, Exception {

		String mailHost = JPropertyReader.getProperty("MAIL.SMTP.HOST");
		String mailPort = JPropertyReader.getProperty("MAIL.SMTP.PORT");
		String mailUser = JPropertyReader.getProperty("SENDER.MAIL.ID");
		String mailProtocal = JPropertyReader
				.getProperty("MAIL.TRANSPORT.PROTOCAL");
		String sendenName = JPropertyReader.getProperty("MAIL.SENDER.NAME");
		String startttlsEnable = JPropertyReader
				.getProperty("MAIL.SMTP.STARTTTLS.ENABLE");
		String mailPassword = JPropertyReader
				.getProperty("SENDER.MAIL.PASSWORD");
		String smtpAuth = JPropertyReader.getProperty("MAIL.SMTP.AUTH");
		FROM_ADDRESS = mailUser;
		SENDER_PASSWORD = mailPassword;

		System.out.println(" mailHost : " + mailHost);
		System.out.println(" mailPort : " + mailPort);
		System.out.println(" mailUser : " + mailUser);
		System.out.println(" mailProtocal : " + mailProtocal);
		System.out.println(" startttlsEnable : " + startttlsEnable);
		System.out.println(" mailPassword : " + mailPassword);
		System.out.println(" smtpAuth : " + smtpAuth);

		System.out.println("Starting send mail ");
		try {
			Properties props = System.getProperties();

			props.put("mail.transport.protocol", mailProtocal);
			props.put("mail.smtp.host", mailHost);
			props.put("mail.smtp.port", mailPort);
			props.put("mail.smtp.user", FROM_ADDRESS);
			props.put("mail.smtp.starttls.enable", startttlsEnable);
			props.put("mail.smtp.auth", smtpAuth);

			String smtpSslTrust = JPropertyReader
					.getProperty("MAIL.SMTP.SSL.TRUST");

			if (smtpSslTrust == null || smtpSslTrust.equalsIgnoreCase("")) {

			} else {
				System.out.println(" smtpSslTrust : " + smtpSslTrust);
				props.setProperty("mail.smtp.ssl.trust", smtpSslTrust);

			}

			Session mailSession = Session.getDefaultInstance(props,
					new javax.mail.Authenticator() {
						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(FROM_ADDRESS,
									SENDER_PASSWORD);
						}
					});

			// SecurityManager security = System.getSecurityManager();

			Authenticator auth = new SMTPAuthenticator();

			// Get a mail session and authenticate user
			Session session = Session.getDefaultInstance(props, auth);

			// Define a new mail message
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(sendenName + "<" + FROM_ADDRESS
					+ ">"));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					toAddress));
			message.setRecipients(Message.RecipientType.CC, ccList);
			message.setSubject(subject);
			// Create a message part to represent the body text
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(Bodymess);

			// use a MimeMultipart as we need to handle the file attachments
			Multipart multipart = new MimeMultipart();

			// add the message body to the mime message
			multipart.addBodyPart(messageBodyPart);

			System.out.println();
			// Put all message parts in the message
			message.setContent(multipart);

			// Send the message

			Transport transport = mailSession.getTransport("smtp");
			transport.connect(mailHost, FROM_ADDRESS, SENDER_PASSWORD);

			transport.sendMessage(message, message.getAllRecipients());
			System.out.println("message sent");
		} catch (Exception e) {

			Logger loggger = Logger.getLogger(SendMail.class);
			loggger.info("Exception in sendMail part ." + e);
			e.printStackTrace();
			return null;
		}
		return "sent";
	}

	// mehtod to authenticate user
	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(FROM_ADDRESS, SENDER_PASSWORD);
		}
	}

	// ************************************************************

	public synchronized String sendMail(EmailContent em) throws IOException {
		String result = null;
		try {
			String[] recipients = em.getMailids();
			String subject = em.getSubject();
			String message = em.getMessage();
			String ccString = "";
			for (int i = 1; i < recipients.length; i++) {
				if (recipients[i] != null && !"".equals(recipients[i])
						&& !"null".equalsIgnoreCase(recipients[i]))
					ccString = ccString + "," + recipients[i];
			}
			if (!ccString.equals("")) {
				ccString = ccString.substring(1);
			}
			InternetAddress[] ccList = InternetAddress.parse(ccString);
			result = sendMail(recipients[0], ccList, subject, message);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public synchronized String sendMailtoChild(String mailid, String username,
			String password) {
		String rst = null;
		try {

			EmailContent em = new EmailContent();
			String[] mails = { mailid };
			em.setMailids(mails);
			em.setSubject("Registration Confirmation Mail");
			em.setMessage("Greetings from E-CAMPUS PRO... \n\n"
					+ "Thank you for Registering with us\n"
					+ "Please use below Url to track / view your child activities in School \n\n"
					+ "URL : "
					+ ClientURL
					+ "\n\n"
					+ "Login Credentials are : \n\nUsername : "
					+ username
					+ "\nPassword : "
					+ password
					+ "\n\n"
					+ "Have a nice day"
					+ "\n\n"
					+ "Regards,\n"
					+ "E-CAMPUS PRO \n---------------------------------------------------\nThis is System generated mail, Please do not reply.");
			rst = new SendMail().sendMail(em);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}

	public synchronized String sendMailtoTeacher(String mailid,
			String username, String password) {
		String rst = null;
		try {

			EmailContent em = new EmailContent();
			String[] mails = { mailid };
			em.setMailids(mails);
			em.setSubject("Registration Confirmation Mail");
			em.setMessage("Greetings from E-CAMPUS PRO... \n\n"
					+ "Thank you for Registering with us\n"
					+ "Please use below Url to track / view / update child activities in School \n\n"
					+ "URL : "
					+ ClientURL
					+ "\n\n"
					+ "Login Credentials are : \n\nUsername : "
					+ username
					+ "\nPassword : "
					+ password
					+ "\n\n"
					+ "Have a nice day"
					+ "\n\n"
					+ "Regards,\n"
					+ "E-CAMPUS PRO \n---------------------------------------------------\nThis is System generated mail, Please do not reply.");
			rst = new SendMail().sendMail(em);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}

	public synchronized String sendMailtoParent(String mailid, String username,
			String password) {
		String rst = null;
		try {

			EmailContent em = new EmailContent();
			String[] mails = { mailid };
			em.setMailids(mails);
			em.setSubject("Registration Confirmation Mail");
			em.setMessage("Greetings from E-CAMPUS PRO... \n\n"
					+ "Thank you for Registering with us\n"
					+ "Please use below Url to track / view your child activities in School \n\n"
					+ "URL : "
					+ ClientURL
					+ "\n\n"
					+ "Login Credentials are : \n\nUsername : "
					+ username
					+ "\nPassword : "
					+ password
					+ "\n\n"
					+ "Have a nice day"
					+ "\n\n"
					+ "Regards,\n"
					+ "E-CAMPUS PRO \n---------------------------------------------------\nThis is System generated mail, Please do not reply.");
			rst = new SendMail().sendMail(em);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}
	
	public String sendMailToParentsOnRequestOfAdmisssionFromOnlinePortal(
			String emailId, String alternateemailId,
			String temporary_admission_id, String studentfirstName,
			String parentId,String dateofbirth) {
		String rst = null;
		try {

			EmailContent em = new EmailContent();
			String[] mails = { emailId ,alternateemailId};
			em.setMailids(mails);
			em.setSubject("Application Confirmation Mail");
			em.setMessage("Dear Parent \n\n "
					+"Greetings from Centris School... \n\n"
					+"Your Application/Admission request number : "+ temporary_admission_id+" for student "+ studentfirstName +"  has been accepted. \n"
					+"We request you to come to the school for Interview/Discussion on :"+ dateofbirth +" \n\n"
					+ "Assuring Bright Future on best Grooming of your Child\n"
					+ "Centris School   \n---------------------------------------------------\nThis is System generated mail, Please do not reply.");
			rst = new SendMail().sendMail(em);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst;
	}

	public String sendMailtoParents(String parentname, String mailid,
			String stuname, String reason, String approveid, String classname, String mobile_number) {
		
		String rst1 = null;
		try {
			System.out.println("classname is "+classname);
			
			if(validateClass(classname).equalsIgnoreCase(classname)){
				EmailContent em = new EmailContent();
				String[] mails = { mailid };
				em.setMailids(mails);
				em.setSubject("Admission Confirmation Mail");
				em.setMessage("Dear Parent");
	            em.setMessage("Greetings from Arya Central School... \n\n"
			     //+parentname +","
				+ "Congratulations!!! \n\n"
				+ "Student " +stuname+ ','+ "application was selected with us:"		
//						+stuname +"\n\n"
//						+"Your status is:" +reason+"\n\n\n"
				        +"\n\n"
						+"Please click on the below link and fill all the details of Online application Form \n\n"
				        +"\n\n"
						+ "URL : "
						 +"\n\n"
						+ ClientURL
						+ "\n"
			            + "\n"
			            + "One Time Login"
			            + "\n"
			            + "\n"
			            + "User Name :"+mobile_number
						+ "\n"
						+ "Password :"+mobile_number
			            + "\n"
			            + "\n"
						+ "Thanks&Regards,\n"
						+ "Admin"
						+"\n---------------------------------------------------\nThis is System generated mail, Please do not reply.");
				rst1 = new SendMail().sendMail(em);
			}else if(validateLkgClass(classname).equalsIgnoreCase(classname)){
				EmailContent em = new EmailContent();
				String[] mails = { mailid };
				em.setMailids(mails);
				em.setSubject("Admission Confirmation Mail");
	            em.setMessage("Greetings from Arya Smart Kids... \n\n"
			     //+parentname +","
				+ "Congratulations!!! \n\n"
				+ "Student " +stuname+','+ "application was selected with us:"		
//						+stuname +"\n\n"
//						+"Your status is:" +reason+"\n\n\n"
					    +"\n\n"
						+"Please click on the below link and fill all the details of Online application Form "
					    +"\n\n"
						+ "URL : "
						+"\n\n"
						+ ClientURL
						+ "\n"
				        + "\n"
				        + "One Time Login"
			            + "\n"
			            + "\n"
			            + "User Name :"+mobile_number
						+ "\n"
						+ "Password :"+mobile_number
			            + "\n"
			            + "\n"
						+ "Thanks&Regards,\n"
						+ "Admin"
						+"\n---------------------------------------------------\nThis is System generated mail, Please do not reply.");
				rst1 = new SendMail().sendMail(em);
			}else if(validateSrSecondaryClass(classname).equalsIgnoreCase(classname)){
				EmailContent em = new EmailContent();
				String[] mails = { mailid };
				em.setMailids(mails);
				em.setSubject("Admission Confirmation Mail");
				em.setMessage("Dear Parent");
	            em.setMessage("Greetings from Arya Central School... \n\n"
			     //+parentname +","
				+ "Congratulations!!! \n\n"
				+ "Student " +stuname+ ','+ "application was selected with us:"		
//						+stuname +"\n\n"
//						+"Your status is:" +reason+"\n\n\n"
				     	+"\n\n"
						+"Please click on the below link and fill all the details of Online application Form "
				     	+"\n\n"
						+ "URL : "
						+"\n\n"
						+ ClientURL
						+ "\n"
	                    + "\n"
						+ "Thanks&Regards,\n"
						+ "Admin"
						+"\n---------------------------------------------------\nThis is System generated mail, Please do not reply.");
				rst1 = new SendMail().sendMail(em);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst1;
	}

	public String sendMailtoParentsForreject(String parentname, String mailid,
			String stuname, String rejectreason) {
		System.out.println("it is going inside reject method for email:");
        String rst1 = null;
		try {

			EmailContent em = new EmailContent();
			String[] mails = { mailid };
			em.setMailids(mails);
			em.setSubject("Regarding Admission"); 
			em.setMessage("\n\n"
    	            + "Dear,"  
					+parentname +"\n"
					+ "Regret to Say, \n\n"
					+stuname
					+" Admission is rejected\n\n"
					+"Reason for Rejection -  " +rejectreason +"\n\n"
					+ "Regards,\n"
					+ "Admin"
	                +"\n---------------------------------------------------\nThis is System generated mail, Please do not reply.");
			     rst1 = new SendMail().sendMail(em);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rst1;
	}
	
		
	
	

	public static void main(String[] args) {
		try {

			EmailContent em = new EmailContent();
			String[] mails = { "dineshkumar.g@centrisinfotech.com" };
			em.setMailids(mails);
			em.setSubject("Registration Confirmation Mail ");
			em.setMessage("Greetings from E-CAMPUS PRO... \n\n"
					+ "Thank you for Registering with us\n"
					+ "Please use below Url to track / view your child activities in School \n\n"
					+ "URL : "
					+ ClientURL
					+ "\n\n"
					+ "Login Credentials are : \n\nUsername : "
					+ "\nPassword : \n\n"
					+ "Have a nice day"
					+ "\n\n"
					+ "Regards,\n"
					+ "E-CAMPUS PRO \n---------------------------------------------------\nThis is System generated mail, Please do not reply.");
			String rst = new SendMail().sendMail(em);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public  String validateClass(String classname) {
		Connection conn = null;
        PreparedStatement pstmt = null;
		ResultSet rt=null;
		String stream = null;
		try{
			conn = JDBCConnection.getSeparateConnection();

			pstmt = conn.prepareStatement(SQLUtilConstants.GET_CLASS_DETAILSLIST);
			pstmt.setString(1, classname.trim());
			System.out.println("pstmt "+pstmt);
			rt=pstmt.executeQuery();

			if(rt.next()){
				stream=rt.getString("classdetails_name_var");
			}else{
				stream="";
			}


		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("returning  stream:" +stream);
		
		return stream;
	}
	
	private String validateLkgClass(String classname) {
		PreparedStatement pstmt = null;
		ResultSet rt=null;
		String stream = null;
		Connection conn = null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_LKG_DETAILS);
			pstmt.setString(1, classname.trim());
			rt=pstmt.executeQuery();
			
			if(rt.next()){
				stream=rt.getString("classdetails_name_var");
			}else{
				stream="";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("rturn stream from primary:" +stream);
		return stream;
	}
	private String validateSrSecondaryClass(String classname) {
		PreparedStatement pstmt = null;
		ResultSet rt=null;
		String stream = null;
		Connection conn = null;
		try{
			conn = JDBCConnection.getSeparateConnection();
			pstmt = conn.prepareStatement(SQLUtilConstants.GET_SRSECONDARY_DETAILS);
			pstmt.setString(1, classname.trim());
			rt=pstmt.executeQuery();
			
			if(rt.next()){
				stream=rt.getString("classdetails_name_var");
			}else{
				stream="";
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("senior secondary class details:" +stream);
		return stream;
	}
}