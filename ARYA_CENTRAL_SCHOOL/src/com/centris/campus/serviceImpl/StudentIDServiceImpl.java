package com.centris.campus.serviceImpl;

import java.util.List;

import com.centris.campus.dao.StudentIDDao;
import com.centris.campus.daoImpl.StudentIDDaoImpl;
import com.centris.campus.service.StudentIDService;
import com.centris.campus.vo.StudentIDVo;

public class StudentIDServiceImpl implements StudentIDService {

	@Override
	public List<StudentIDVo> getstudentIDPDFReport(String accyear, String section, String className, String student) {
		StudentIDDao stulist=new StudentIDDaoImpl();
		return stulist.getstudentIDPDFReport(accyear,section,className,student);
	}

	
	@Override
	public List<StudentIDVo> getstudentBusIDPDFReport(String[] accyear,
			String[] locationId, String[] streamId, String[] studentId) {
		StudentIDDao stulist=new StudentIDDaoImpl();
		return stulist.getstudentBusIDPDFReport(accyear,locationId,streamId,studentId);
	}

}
