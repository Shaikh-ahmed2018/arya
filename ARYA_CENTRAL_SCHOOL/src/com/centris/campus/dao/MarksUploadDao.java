package com.centris.campus.dao;
import java.util.ArrayList;
import java.util.List;

import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.ScoredMarksEntryVO;
import com.centris.campus.vo.StudentMarksDisplayVO;

public interface MarksUploadDao {
	public String getMarksUpload(MarksUploadVO uploadmarks) throws Exception;
	public List<ScoredMarksEntryVO> showStudentMarks(StudentMarksDisplayVO studentmarksdisplay) throws Exception;
	public List<ScoredMarksEntryVO> getStudentMarks(StudentMarksDisplayVO studentmarksdisplay);
	public ArrayList<MarksUploadVO>  getMarksUploadList(String serchterm);
	
}
