package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.service.StudentIDService;
import com.centris.campus.serviceImpl.StudentIDServiceImpl;
import com.centris.campus.vo.StudentIDVo;

public class StudentIDBD {

	public List<StudentIDVo> getstudentIDPDFReport(String accyear, String section, String className, String student) {
		StudentIDService stuList= new StudentIDServiceImpl();
		
		return stuList.getstudentIDPDFReport(accyear,section,className,student);
	}

	public List<StudentIDVo> getstudentBusIDPDFReport(String[] accyear,
			String[] locationId, String[] streamId, String[] studentId) {
		
		StudentIDService stuList= new StudentIDServiceImpl();
		return stuList.getstudentBusIDPDFReport(accyear,locationId,streamId,studentId);
	}

}
