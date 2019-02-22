package com.centris.campus.delegate;

import java.util.List;

import com.centris.campus.pojo.ClassPojo;
import com.centris.campus.pojo.SectionPojo;
import com.centris.campus.service.StudentTransferCertifivateReportService;
import com.centris.campus.serviceImpl.StudentTransferCertifivateReportServiceImpl;
import com.centris.campus.vo.ParentVO;

public class StudentTransferCertifivateReportBD {

	
	StudentTransferCertifivateReportService service = new StudentTransferCertifivateReportServiceImpl();
	
	public List<ClassPojo> getClassDetails() {
		
		return service.getClassDetailsService();
	}

	public List<SectionPojo> getSectionList(String classname, String location) {
		
		return service.getSectionListService(classname,location);
	}

	public List<ParentVO> getAllStudentNamesReportBD(String sectionid) {

		return service.getAllStudentNamesReportService(sectionid);
	}

	

}
