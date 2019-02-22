package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.forms.LeaveDetailsReportForm;
import com.centris.campus.vo.LeaveDetailsReportVo;
import com.centris.campus.vo.TeacherVo;

public interface LeaveDetailsReportDao {
	
	public  ArrayList<LeaveDetailsReportVo> getLeaveDetails(LeaveDetailsReportForm leaveDetailsReportVo);
	public  ArrayList<TeacherVo> getTeachername(String teachingtype,String dept);

}
