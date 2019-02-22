package com.centris.campus.admin;

import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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

import com.centris.campus.util.JPropertyReader;

public class SendMail {
	
	String FROM_ADDRESS = "donotreply@centrisinfotech.com";
	String SENDER_PASSWORD = "DoN%rP)987";
	public static String senderName = "CENTRIS"; 
	public static String hostName = "mail.centrisinfotech.com";    
	public static String fromMailId = ""; 
	public static String password = "";        
	public static String authentication = "true";
	public static String portNumber = "25";           
	public static String protocol = "smtp";  
	
	public void sendMail(String email,EmailContent em){
		
		System.out.println("----sendmail is calling----------");
		String to = email;
		String subject ="Arya Central School";
		String body = em.getContent()+"\n"+"Your Username is "+em.getUsername()+" & Password is "+em.getPassword();
		String filename = "D:\\jaga\\abc.txt";
		Properties props = null;
		Transport t = null;
		MimeMessage message = null;
		try {
			props = System.getProperties();
			props.put("mail.smtp.starttls.enable", authentication);
			props.put("mail.smtp.host", hostName);
			props.put("mail.smtp.port", portNumber);
			props.put("mail.smtp.auth", authentication);
			props.put("mail.smtp.user", fromMailId);
			props.put("mail.smtp.password", password);
			javax.mail.Session mailSession = javax.mail.Session.getInstance(props, null);			
			message = new MimeMessage(mailSession);
			
			BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(body);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            
			Calendar today = Calendar.getInstance();
			message.setSentDate(today.getTime());
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));					
			message.setFrom(new InternetAddress(senderName + "<" + fromMailId + ">"));
					
			message.setSubject(subject);
			message.saveChanges();
			t = mailSession.getTransport(protocol);
			t.connect(hostName, fromMailId, password);
			t.sendMessage(message, message.getAllRecipients());
			if (t != null) {
				System.out.println("Email Sent Successfully");
			}
		} catch (Exception e) {
			System.out.println("Exception in MailSend() :"+ e);					

		}

	}
	public synchronized String sendMail(EmailContent em) throws IOException {
		String result = null;
		try {
			String[] recipients = em.getMailids();
			System.out.println("recipients ");
			String subject = em.getSubject();
			String message = em.getContent();
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
	private class SMTPAuthenticator extends javax.mail.Authenticator {
		public PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(FROM_ADDRESS, SENDER_PASSWORD);
		}
	}
}
