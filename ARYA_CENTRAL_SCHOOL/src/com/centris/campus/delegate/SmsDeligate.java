package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UniformSmsPojo;
import com.centris.campus.service.SmsService;
import com.centris.campus.serviceImpl.SmsServiceImpl;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.SmsVo;
import com.centris.campus.vo.StudentInfoVO;

public class SmsDeligate {

	SmsService service=new SmsServiceImpl();
	
	public List<ClassPojo> getclasslist() {
		
		return service.getclasslistService();
	}

	public ArrayList<SectionPojo> getsectionlist(String classid) {
		
		return service.getsectionlistService(classid);
	}

	public ArrayList<SectionPojo> getsubjectlist(String classid) {
		
		return service.getsubjectlistService(classid);
	}

	public String inserthomework(SmsVo vo) {
		
		return service.inserthomeworkService(vo);
	}

	public List<SmsVo> getHomeWorklist() {
		
		return service.getHomeWorklistService();
	}

	public List<SmsVo> getHomeWorkSearchlist(String searchTerm) {
		return service.getHomeWorkSearchlistService(searchTerm);
	}

	public ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetails() {
		
		return service.getMeetingListDetailsService();
	}

	public ArrayList<LstmsUpcomingMeetingVO> getMeetingSearchListDetails(
			String searchTerm) {
		// TODO Auto-generated method stub
		return null;
	}

public ArrayList<StudentInfoVO> getStudentListDetails(String[] categoryval) {
		
		return service.getStudentListDetailsService(categoryval);
	}

public ArrayList<SubjectPojo> getSubjectListDetails(String[] categoryval) {
	
	return service.getSubjectListDetailsService(categoryval);
}

public String saveMeetingDetails(LstmsUpcomingMeetingVO meetingvo) {
	
	return service.saveMeetingDetailsService(meetingvo);
}

public ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails() {
	return service.getlatecomersListDetails();
}

public String addlatecomers(LstmsUpcomingMeetingVO meetingvo) {
	return service.addlatecomers(meetingvo);
}


public ArrayList<UniformSmsPojo> getUniformListDetails() {
	return service.getUniformListDetailsService();
}

public String storeUniformDetails(UniformSmsPojo upojo) {
	
	return service.storeUniformDetails(upojo);
}

public String insertOtherSMS(UniformSmsPojo upojo) {
	return service.insertOtherSMS(upojo);
}

	/*public List<SmsVo> getHomeWorkSearchlist(String searchTerm) {
		
		return service.getHomeWorkSearchlistService(searchTerm);
	}

	public String deletehomework(SmsVo vo) {
		
		
		
		return service.deletehomeworkService(vo);
	}

	public ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetails() {
		
		return service.getMeetingListDetailsService();
	}

	public ArrayList<SubjectPojo> getSubjectListDetails(String[] categoryval) {
		
		return service.getSubjectListDetailsService(categoryval);
	}

	public ArrayList<StudentInfoVO> getStudentListDetails(String[] categoryval) {
		
		return service.getStudentListDetailsService(categoryval);
	}

	

	public String deleteMeetingDeligate(SmsVo vo) {
		
		return service.deleteMeetingService(vo);
	}

	public ArrayList<LstmsUpcomingMeetingVO> getMeetingSearchListDetails(
			String searchTerm) {
		
		return service.getMeetingSearchListDetailsService(searchTerm);
	}


	public String storeUniformDetails(UniformSmsPojo upojo) {
		
		return service.storeUniformDetails(upojo);
	}


	public String addlatecomers(LstmsUpcomingMeetingVO meetingvo) 

	
	{
	
		return service.addlatecomers(meetingvo);
	
	}

	public ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails() 
	
	{
		
		return service.getlatecomersListDetails();
	}

	public ArrayList<UniformSmsPojo> getUniformListDetails() {
		
		return service.getUniformListDetailsService();
	}

	

	public ArrayList<UniformSmsPojo> getBdayListDetails() {
		
		return service.getBdayListDetailsService();
	}*/
	
	
	
	
	

}
