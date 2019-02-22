package com.centris.campus.dao;

import java.util.List;

import com.centris.campus.vo.StudentIDVo;

public interface StudentIDDao {

	List<StudentIDVo> getstudentIDPDFReport(String accyear, String section, String className, String student);

	List<StudentIDVo> getstudentBusIDPDFReport(String[] accyear, String[] locationId,
			String[] streamId, String[] studentId);

}
