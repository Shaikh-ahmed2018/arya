package com.centris.campus.service;

import java.util.List;

import com.centris.campus.vo.StudentIDVo;

public interface StudentIDService {

	List<StudentIDVo> getstudentIDPDFReport(String accyear, String section, String className, String student);

	List<StudentIDVo> getstudentBusIDPDFReport(String[] accyear, String[] locationId,
			String[] streamId, String[] studentId);

}
