package com.centris.campus.delegate;

import java.util.ArrayList;
import java.util.List;

import com.centris.campus.daoImpl.MarksUploadDaoImpl;
import com.centris.campus.service.MarksUploadService;
import com.centris.campus.serviceImpl.MarksUploadServiceImpl;
import com.centris.campus.vo.MarksUploadVO;
import com.centris.campus.vo.ScoredMarksEntryVO;
import com.centris.campus.vo.StudentMarksDisplayVO;

public class MarksUploadDelegate {
	MarksUploadService marksUploadService;
	public String getMarksUpload(MarksUploadVO uploadmarks) throws Exception {
		return new MarksUploadServiceImpl().getMarksUpload(uploadmarks);

	}
	
	public List<ScoredMarksEntryVO> showStudentMarks(StudentMarksDisplayVO studentmarksdisplay) throws Exception {
		return new MarksUploadServiceImpl().showStudentMarks(studentmarksdisplay);

	}
	
	public List<ScoredMarksEntryVO> getStudentMarks(StudentMarksDisplayVO studentmarksdisplay) {
		return new MarksUploadServiceImpl().getStudentMarks(studentmarksdisplay);
	}
	
	public ArrayList<MarksUploadVO> getMarksUploadList(String serchterm) throws Exception {
		
		return new MarksUploadServiceImpl().getMarksUploadList(serchterm);

	}
	
	
}
