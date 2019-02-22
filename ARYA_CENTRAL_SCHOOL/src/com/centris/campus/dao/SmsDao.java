package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.UniformSmsPojo;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.SmsVo;
import com.centris.campus.vo.StudentInfoVO;

public interface SmsDao {

	List<ClassPojo> getclasslistDao();

	ArrayList<SectionPojo> getsectionlistDao(String classid);

	ArrayList<SectionPojo> getsubjectlistDao(String classid);

	String inserthomeworDao(SmsVo vo);

	List<SmsVo> getHomeWorklistDao();

	List<SmsVo> getHomeWorkSearchlistDao(String searchTerm);

	ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetailsServiceDao();

	ArrayList<StudentInfoVO> getStudentListDetailsDao(String[] categoryval);

	ArrayList<SubjectPojo> getSubjectListDetailsDao(String[] categoryval);

	String saveMeetingDetailsDao(LstmsUpcomingMeetingVO meetingvo);

	ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails();

	String addlatecomers(LstmsUpcomingMeetingVO meetingvo);

	ArrayList<UniformSmsPojo> getUniformListDetailsDao();

	int storeUniformStudent(UniformSmsPojo upojo);

	String insertOtherSMS(UniformSmsPojo upojo);

	/*String deletehomeworkDao(SmsVo vo);

	

	

	ArrayList<StudentInfoVO> getStudentListDetailsDao(String[] categoryval);

	

	String deleteMeetingDao(SmsVo vo);

	ArrayList<LstmsUpcomingMeetingVO> getMeetingSearchListDetailsDao(
			String searchTerm);

	String addlatecomers(LstmsUpcomingMeetingVO meetingvo);

	ArrayList<LstmsUpcomingMeetingVO> getlatecomersListDetails();

	int storeUniformDetails(UniformSmsPojo upojo);

	int storeUniformSections(UniformSmsPojo upojo);

	int storeUniformStudent(UniformSmsPojo upojo);

	ArrayList<UniformSmsPojo> getUniformListDetailsDao();


	ArrayList<UniformSmsPojo> getBdayListDetailsDao();*/
	
	

}
