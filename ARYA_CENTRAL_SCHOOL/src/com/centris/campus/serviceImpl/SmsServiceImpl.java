package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.dao.SmsDao;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.SmsDaoIMPL;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UniformSmsPojo;
import com.centris.campus.service.SmsService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.SmsVo;
import com.centris.campus.vo.StudentInfoVO;

public class SmsServiceImpl implements SmsService{
	
	private static final Logger logger = Logger
			.getLogger(SmsServiceImpl.class);
	
	SmsDao dao = new SmsDaoIMPL();

	
	public List<ClassPojo> getclasslistService() {
		
		return dao.getclasslistDao();
	}


	
	public ArrayList<SectionPojo> getsectionlistService(String classid) {
		
		return dao.getsectionlistDao(classid);
	}



	
	public ArrayList<SectionPojo> getsubjectlistService(String classid) {
		
		return dao.getsubjectlistDao(classid);
	}



	
	public String inserthomeworkService(SmsVo vo) {
		
		return dao.inserthomeworDao(vo);
	}



	
	public List<SmsVo> getHomeWorklistService() {
		
		return dao.getHomeWorklistDao();
	}



	
	public List<SmsVo> getHomeWorkSearchlistService(String searchTerm) {
		
		return dao.getHomeWorkSearchlistDao(searchTerm);
	}


	public ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetailsService() {
		
		return dao.getMeetingListDetailsServiceDao();
	}



	
	public ArrayList<StudentInfoVO> getStudentListDetailsService(
			String[] categoryval) {
		
		return dao.getStudentListDetailsDao(categoryval);
	}


	public ArrayList<SubjectPojo> getSubjectListDetailsService(
			String[] categoryval) {
		
		return dao.getSubjectListDetailsDao(categoryval);
	}
	
	
public String saveMeetingDetailsService(LstmsUpcomingMeetingVO meetingvo) {
		
		return dao.saveMeetingDetailsDao(meetingvo);
	}




public ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails() {
	
	return dao.getlatecomersListDetails();
}




public String addlatecomers(LstmsUpcomingMeetingVO meetingvo) {
	
	return dao.addlatecomers(meetingvo);
}




public ArrayList<UniformSmsPojo> getUniformListDetailsService() {
	
	return dao.getUniformListDetailsDao();
}




public String storeUniformDetails(UniformSmsPojo upojo) {
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.START_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SmsServiceImpl: storeUniformDetails Starting");
	
	String status = null;
	String uniformcode = null;
	int count = 0;
	int count1 = 0;
	int count2 = 0;
	
	try {
		
		uniformcode = IDGenerator.getPrimaryKeyID("sms_uniform_details");
		
		upojo.setIssection(1);
		upojo.setIsstudent(0);
		upojo.setUniformcode(uniformcode);
		
		count2 = dao.storeUniformStudent(upojo);
		
		if(count2==1)
		{
			status="Uniform Details Sent Successfully";
			
		}
		else
		{
			status="Uniform Details Sending Failed";
			
		}
		
		
	} catch (Exception e) {
		logger.error(e.getMessage(), e);
		e.printStackTrace();
	}
	
	
	logger.setLevel(Level.DEBUG);
	JLogger.log(0, JDate.getTimeString(new Date())
			+ MessageConstants.END_POINT);
	logger.info(JDate.getTimeString(new Date())
			+ " Control in SmsServiceImpl: storeUniformDetails Ending");
	
	return status;
}



@Override
public String insertOtherSMS(UniformSmsPojo upojo) {
	return dao.insertOtherSMS(upojo);
}
	
	
	/*public String deletehomeworkService(SmsVo vo) {
		
		return dao.deletehomeworkDao(vo);
	}



	
	



	
	



	
	public ArrayList<StudentInfoVO> getStudentListDetailsService(
			String[] categoryval) {
		
		return dao.getStudentListDetailsDao(categoryval);
	}



	
	



	
	public String deleteMeetingService(SmsVo vo) {
		
		return dao.deleteMeetingDao(vo);
	}



	
	public ArrayList<LstmsUpcomingMeetingVO> getMeetingSearchListDetailsService(
			String searchTerm) {
		
		
		
		return dao.getMeetingSearchListDetailsDao(searchTerm);
	}

	
	public String storeUniformDetails(UniformSmsPojo upojo) {
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsServiceImpl: storeUniformDetails Starting");
		
		String status = null;
		String uniformcode = null;
		int count = 0;
		int count1 = 0;
		int count2 = 0;
		
		try {
			
			uniformcode = IDGenerator.getPrimaryKeyID("sms_uniform_details");
			
			upojo.setIssection(1);
			upojo.setIsstudent(0);
			upojo.setUniformcode(uniformcode);
			
			count2 = dao.storeUniformStudent(upojo);
			
			if(count2==1)
			{
				status="Uniform Details Sent Successfully";
				System.out.println(status);
			}
			else
			{
				status="Uniform Details Sending Failed";
				System.out.println(status);
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsServiceImpl: storeUniformDetails Ending");
		
		return status;
	}




	public String addlatecomers(LstmsUpcomingMeetingVO meetingvo) 
	
	{
	
		return dao.addlatecomers(meetingvo);
	
	}



	public ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails() 
	{
		return dao.getlatecomersListDetails();
	}



	
	public ArrayList<UniformSmsPojo> getUniformListDetailsService() {
		
		return dao.getUniformListDetailsDao();
	}

	
	public String storeBdatDetailsService(UniformSmsPojo upojo) {
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsServiceImpl: storeBdatDetailsService Starting");
		
		String status = null;
		String uniformcode = null;
		int count = 0;
		int count1 = 0;
		int count2 = 0;
		
		try {
			
			uniformcode = IDGenerator.getPrimaryKeyID("birthday_sms");
			
			upojo.setIssection(1);
			upojo.setIsstudent(0);
			upojo.setUniformcode(uniformcode);
			
			count2 = dao.storeBdatDetailsDao(upojo);
			
			if(count2==1)
			{
				status="BirthDay Sent Successfully";
				System.out.println(status);
			}
			else
			{
				status="BirthDay Sending Failed";
				System.out.println(status);
			}
			
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in SmsServiceImpl: storeBdatDetailsService Ending");
		return status;
	}



	
	public ArrayList<UniformSmsPojo> getBdayListDetailsService() {
		
		return dao. getBdayListDetailsDao();
	}*/


}
