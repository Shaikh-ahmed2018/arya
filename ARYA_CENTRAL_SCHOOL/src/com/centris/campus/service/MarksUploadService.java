package com.centris.campus.service;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.ScoredMarksEntryVO;
import com.centris.campus.vo.StudentMarksDisplayVO;

public interface MarksUploadService {
	public String getMarksUpload(MarksUploadVO uploadmarks);
	public List<ScoredMarksEntryVO> showStudentMarks(StudentMarksDisplayVO studentmarksdisplay);
	public List<ScoredMarksEntryVO> getStudentMarks(StudentMarksDisplayVO studentmarksdisplay);
	public ArrayList<MarksUploadVO>  getMarksUploadList(String serchterm);
}
