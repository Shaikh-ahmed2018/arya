package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;


import com.centris.campus.forms.ClassForm;
import com.centris.campus.forms.RemarksForm;
import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.pojo.SubjectPojo;
import com.centris.campus.pojo.TeacherRegistrationPojo;
import com.centris.campus.service.CommunicationSettingsService;
import com.centris.campus.serviceImpl.CommunicationSettingsServiceImpl;
import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.RemarksVo;
import com.centris.campus.vo.StreamDetailsVO;
import com.centris.campus.vo.StudentInfoVO;
import com.centris.campus.vo.UpcomingBdayVo;
import com.centris.campus.vo.UpcomingRemarksVO;

public class CommunicationSettingsBD {

	
	CommunicationSettingsService service = new CommunicationSettingsServiceImpl();
	
	
	public ArrayList<UpcomingRemarksVO> getRemarksListDetails() {
		
		return service.getRemarksListDetailsService();
	}


	public List<StreamDetailsVO> getStreamListDetails() {
		
		return service.getStreamListDetailsService();
	}


	public List<ClassPojo> getClassListDetails(ClassPojo pojo) {
		
		return service.getClassListDetailsService(pojo);
	}


	public ArrayList<SectionPojo> getSectionListDetails(String[] categoryval) {
		
		return service.getSectionListDetailsService(categoryval);
	}


	public ArrayList<StudentInfoVO> getStudentListDetails(
			StudentInfoVO studentvo) {
		
		return service.getStudentListDetailsService(studentvo);
	}


	public ArrayList<TeacherRegistrationPojo> getTeacherListDetails(
			TeacherRegistrationPojo teacherpojo) {
		
		return service.getTeacherListDetailsService(teacherpojo);
	}


	public ArrayList<SubjectPojo> getSubjectListDetails(SubjectPojo subpojo) {
	
		return service.getSubjectListDetailsService(subpojo);
	}


	public String saveRemarkDetails(RemarksVo remarkform) {
		
		
		
		return service.saveRemarkDetailsService(remarkform);
	}


	public ArrayList<UpcomingRemarksVO> searchRemarkDetails(UpcomingRemarksVO remrakvo) {
		
		return service.searchRemarkDetailsService(remrakvo);
	}


	public boolean validateRemarkBD(RemarksVo remarkform) {
		
		return service.validateRemarkService(remarkform);
	}


	public ArrayList<LstmsUpcomingMeetingVO> getMeetingListDetails() {
		
		return service.getMeetingListDetailsService();
	}


	public String saveMeetingDetails(LstmsUpcomingMeetingVO meetingvo) {
		
		return service.saveMeetingDetailsService(meetingvo);
	}


	public ArrayList<LstmsUpcomingMeetingVO> searchMeetingDetails(
			LstmsUpcomingMeetingVO meetingvo) {
		
		return service.searchMeetingDetailsService(meetingvo);
	}


	public String createBdayDetails(UpcomingBdayVo bdayvo) {
		
		return service.createBdayDetailsService(bdayvo);
	}


	public ArrayList<UpcomingBdayVo> getBdayListDetails() {
		
		return service.getBdayDetailsService();
	}


	public ArrayList<UpcomingBdayVo> searchBdayDetails(UpcomingBdayVo bdayvo) {
		
		return service.searchBdayDetailsService(bdayvo);
	}


	public RemarksVo editRemarkDetailsBD(RemarksVo remarkvo) {
		
		return service.editRemarkDetailsService(remarkvo);
	}

	
	
	
	
}
