package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.pojo.BdayPojo;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.MeetingPojo;
import com.centris.campus.pojo.RemarksPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.RemarksVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.UpcomingBdayVo;
import com.centris.campus.vo.UpcomingRemarksVO;

public interface CommunicationSettingsDao {

	ArrayList<UpcomingRemarksVO> getRemarksListDetailsDao();

	List<StreamDetailsVO> getStreamListDetailsDao();

	

	List<ClassPojo> getClassListDetailsDao(ClassPojo pojo);

	ArrayList<SectionPojo> getSectionListDetailsDao(String[] categoryval);

	ArrayList<StudentInfoVO> getStudentListDetailsDao(StudentInfoVO studentvo);

	ArrayList<TeacherRegistrationPojo> getTeacherListDetailsDao(
			TeacherRegistrationPojo teacherpojo);

	ArrayList<SubjectPojo> getSubjectListDetailsDao(SubjectPojo subpojo);

	String saveRemarkDetailsDao(RemarksPojo remarkpojo);

	ArrayList<UpcomingRemarksVO> getRemarkListDetailsDao(UpcomingRemarksVO remrakvo);

	boolean validateRemarkDao(RemarksVo remarkform);

	ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetailsDao();

	String saveMeetingDetailsDao(MeetingPojo pojo);


	ArrayList<LstmsUpcomingMeetingVO> searchMeetingListDetailsDao(
			LstmsUpcomingMeetingVO meetingvo);

	String createBdayDetailsDao(BdayPojo bdaypojo);

	ArrayList<UpcomingBdayVo> getBdayListDetailsDao();

	ArrayList<UpcomingBdayVo> searchBadyListDetailsDao(UpcomingBdayVo bdayvo);

	RemarksVo editRemarkDetailsDao(RemarksVo remarkvo);

}
