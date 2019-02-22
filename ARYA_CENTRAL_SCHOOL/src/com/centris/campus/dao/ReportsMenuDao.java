package com.centris.campus.dao;

import java.util.ArrayList;
import java.util.HashMap;

import com.centris.campus.forms.ReportMenuForm;
import com.centris.campus.pojo.FeeStatusReportPojo;
import com.centris.campus.pojo.MarksPOJO;
import com.centris.campus.vo.ExaminationDetailsVo;
import com.centris.campus.vo.FeeReportDetailsVo;
import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.ReportMenuVo;
import com.centris.campus.vo.StudentInfoVO;

public interface ReportsMenuDao {
	
	public ArrayList<ReportMenuVo> getAccYears();
	public ArrayList<ReportMenuVo> getStream();
	public ArrayList<ReportMenuVo> getClassesByStream(String streamId);
	public ArrayList<ReportMenuVo> getSectionsByClass(String classId, String location);
	public ArrayList<StudentInfoVO> getStudentDetailsReport(ReportMenuForm reform);
	public ReportMenuVo  getSelectedItems(ReportMenuForm reform);
	public HashMap<String, ArrayList<FeeReportDetailsVo>> getStdFeeStatusReportDetails(ReportMenuForm reform);
	public ArrayList<FeeReportDetailsVo> getStdFeeStatusReportDownload(FeeStatusReportPojo reform);
	public HashMap<String, ArrayList<MarksUploadVO>> getStdMarksDetails(ReportMenuForm reform);
	public  ArrayList<MarksUploadVO> getStdMarksDetailsDownload(MarksPOJO reform);
	public ArrayList<ExaminationDetailsVo> examReportClassWiseDetails(ReportMenuForm reform);
	public ArrayList<StudentInfoVO> geInactivetStudentDetailsReport(ReportMenuForm reform);
	public ArrayList<StudentInfoVO> geInactivetStudentFeeDetailsReport(ReportMenuForm reform);
	public ReportMenuVo  getSelectedoneItems(ReportMenuForm reform);
	public String gettempregid();
	public String getthirdRegNo();
	public ArrayList<ReportMenuVo> getStudentClass(String location);
	public ArrayList<ReportMenuVo> getstudentDOBWise(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentFatherOccuWise(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentMotherOccuWise(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentDetailsReligionWise(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentCategoryWise(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getclasssectionDetails(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentParentWise(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentList(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentContactDetails(ReportMenuVo vo);
	public ArrayList<ReportMenuVo> getstudentStandardWise(ReportMenuVo vo);
	ArrayList<ReportMenuVo> getClassesByStream(String streamId, String location);
	public ArrayList<ReportMenuVo> getSectionsByClassLoc(String classId,
			String location);

	


}
