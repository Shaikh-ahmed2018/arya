package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.forms.ClassForm;
import com.centris.campus.forms.RemarksForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.RemarksVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.UpcomingBdayVo;
import com.centris.campus.vo.UpcomingRemarksVO;

public interface CommunicationSettingsService {

	ArrayList<UpcomingRemarksVO> getRemarksListDetailsService();

	List<StreamDetailsVO> getStreamListDetailsService();

	List<ClassPojo> getClassListDetailsService(ClassPojo pojo);

	ArrayList<SectionPojo> getSectionListDetailsService(String[] categoryval);

	ArrayList<StudentInfoVO> getStudentListDetailsService(
			StudentInfoVO studentvo);

	ArrayList<TeacherRegistrationPojo> getTeacherListDetailsService(
			TeacherRegistrationPojo teacherpojo);

	ArrayList<SubjectPojo> getSubjectListDetailsService(SubjectPojo subpojo);

	String saveRemarkDetailsService(RemarksVo remarkform);

	ArrayList<UpcomingRemarksVO> searchRemarkDetailsService(UpcomingRemarksVO remrakvo);

	boolean validateRemarkService(RemarksVo remarkform);

	ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetailsService();

	String saveMeetingDetailsService(LstmsUpcomingMeetingVO meetingvo);

	ArrayList<LstmsUpcomingMeetingVO> searchMeetingDetailsService(
			LstmsUpcomingMeetingVO meetingvo);

	String createBdayDetailsService(UpcomingBdayVo bdayvo);

	ArrayList<UpcomingBdayVo> getBdayDetailsService();

	ArrayList<UpcomingBdayVo> searchBdayDetailsService(UpcomingBdayVo bdayvo);

	RemarksVo editRemarkDetailsService(RemarksVo remarkvo);

}
