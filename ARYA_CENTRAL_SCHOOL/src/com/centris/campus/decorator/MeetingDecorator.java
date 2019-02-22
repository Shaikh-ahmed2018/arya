package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.LstmsUpcomingMeetingVO;
import com.centris.campus.vo.UpcomingRemarksVO;

public class MeetingDecorator  extends TableDecorator {
	
	public  String getCheck() {

		LstmsUpcomingMeetingVO meetingVO = (LstmsUpcomingMeetingVO) getCurrentRowObject();

		 String check = "<input  type=\"checkbox\" class=\"class_Checkbox_meeting\" id=\"check_"
				 
				 
				 +meetingVO.getTitle()+ "\","+ "\""
				 +meetingVO.getMeetingDate()+ "\","+ "\""
				 +meetingVO.getStartTime()+ "\","+ "\""
				 +meetingVO.getEndTime()+ "\","+ "\""
				 +meetingVO.getDescription()+ "\","+ "\""
				 
		
				
				
					+"\" />";
			

		return check;
	
	
	
	}
	
	

}
