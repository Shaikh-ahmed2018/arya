package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.AssignmentViewVO;

public class ParentAssignmentDecorator extends TableDecorator {
	
	
	public  String getCheck() {
		
		AssignmentViewVO assvo =  (AssignmentViewVO) getCurrentRowObject();
		
		  
           String check = "<input  type=\"checkbox\" class=\"class_Checkbox_assignment\" id=\"check_"
				 
				 
        		  +assvo.getClassid() + "\","+ "\""
        		  +assvo.getClassname() + "\","+ "\""
        		  +assvo.getSectionid() + "\","+ "\""
        		  +assvo.getSectionname()+ "\","+ "\""
        		  +assvo.getAssignmentname()+ "\","+ "\""
        		  +assvo.getAssignmentdate() + "\","+ "\""
        		  +assvo.getActualcompletedate() + "\","+ "\""
        		  +assvo.getAcquiredmarks() + "\","+ "\""
        		  +assvo.getMaxmarks() + "\","+ "\""
        		  +assvo.getDescription() + "\","+ "\""
        		  +assvo.getRemarks() + "\","+ "\""
        		  +assvo.getStudentid() + "\","+ "\""
        		  +assvo.getAssignmentid() + "\","+ "\""
        		   
        		   
			
				
				/* +meetingVO.getTitle()+ "\","+ "\""
				 +meetingVO.getMeetingDate()+ "\","+ "\""
				 +meetingVO.getStartTime()+ "\","+ "\""
				 +meetingVO.getEndTime()+ "\","+ "\""
				 +meetingVO.getDescription()+ "\","+ "\""*/
				 
		
				
				
					+"\" />";
		
       	System.out.println("studentid"+assvo.getStudentid());
		return check;
		
		
		
		
	}

}
