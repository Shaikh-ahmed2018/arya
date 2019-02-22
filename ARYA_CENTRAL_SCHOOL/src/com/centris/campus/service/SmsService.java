package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UniformSmsPojo;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.SmsVo;
import com.centris.campus.vo.StudentInfoVO;

public interface SmsService {

	List<ClassPojo> getclasslistService();

	ArrayList<SectionPojo> getsectionlistService(String classid);

	ArrayList<SectionPojo> getsubjectlistService(String classid);

	String inserthomeworkService(SmsVo vo);

	List<SmsVo> getHomeWorklistService();

	List<SmsVo> getHomeWorkSearchlistService(String searchTerm);

	ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetailsService();

	ArrayList<StudentInfoVO> getStudentListDetailsService(String[] categoryval);

	ArrayList<SubjectPojo> getSubjectListDetailsService(String[] categoryval);

	String saveMeetingDetailsService(LstmsUpcomingMeetingVO meetingvo);

	ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails();

	String addlatecomers(LstmsUpcomingMeetingVO meetingvo);

	ArrayList<UniformSmsPojo> getUniformListDetailsService();

	String storeUniformDetails(UniformSmsPojo upojo);

	String insertOtherSMS(UniformSmsPojo upojo);

	/*String deletehomeworkService(SmsVo vo);

	ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetailsService();

	ArrayList<SubjectPojo> getSubjectListDetailsService(String[] categoryval);

	ArrayList<StudentInfoVO> getStudentListDetailsService(String[] categoryval);

	String saveMeetingDetailsService(LstmsUpcomingMeetingVO meetingvo);

	String deleteMeetingService(SmsVo vo);

	ArrayList<LstmsUpcomingMeetingVO> getMeetingSearchListDetailsService(
			String searchTerm);


	String storeUniformDetails(UniformSmsPojo upojo);


	String addlatecomers(LstmsUpcomingMeetingVO meetingvo);

	ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails();

	ArrayList<UniformSmsPojo> getUniformListDetailsService();


	ArrayList<UniformSmsPojo> getBdayListDetailsService();*/

	
	
	
	
	
	

}
