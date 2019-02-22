package com.centris.campus.delegate;

import java.util.ArrayList;

import com.centris.campus.pojo.StudentCertificatesPOJO;
import com.centris.campus.service.StudentCertificatesService;
import com.centris.campus.serviceImpl.StudentCertificatesServiceImpl;
import com.centris.campus.vo.StudentCertificatesVo;
import com.centris.campus.vo.StudentRegistrationVo;

public class StudentCertificatesBD {

	static StudentCertificatesService service;
	
	static{
		 service = new StudentCertificatesServiceImpl();
	}
	
	public ArrayList<StudentCertificatesVo> getStudentsDetails(String loc, String acyear) {
		return service.getStudentsDetails(loc,acyear);
	}

	public StudentCertificatesVo getStudentCertificate(StudentCertificatesPOJO pojo) {
		return service.getStudentCertificate(pojo);
	}

	public String saveAgeCertificateData(StudentCertificatesPOJO pojo) {
		return service.saveAgeCertificateData(pojo);
	}

	public ArrayList<StudentCertificatesVo> getissueddetails(StudentCertificatesPOJO pojo) {
		return service.getissueddetails(pojo);
	}

	public String saveBonafiedCertificateData(StudentCertificatesPOJO pojo) {
		return service.saveBonafiedCertificateData(pojo);
	}

	public ArrayList<StudentCertificatesVo> getbonafiedissueddetails(StudentCertificatesPOJO pojo) {
		return service.getbonafiedissueddetails(pojo);
	}

	public String saveCounductedCertificateData(StudentCertificatesPOJO pojo) {
		return service.saveCounductedCertificateData(pojo);
	}

	public ArrayList<StudentCertificatesVo> getconductissueddetails(StudentCertificatesPOJO pojo) {
		return service.getconductissueddetails(pojo);
	}

	public String saveCourseCounductedCertificateData(StudentCertificatesPOJO pojo) {
		return service.saveCourseCounductedCertificateData(pojo);
	}

	public ArrayList<StudentCertificatesVo> getcourseconductissueddetails(StudentCertificatesPOJO pojo) {
		return service.getcourseconductissueddetails(pojo);
	}

	public String saveStudentVisaCertificateData(StudentCertificatesPOJO pojo) {
		return service.saveStudentVisaCertificateData(pojo);
	}

	public ArrayList<StudentCertificatesVo> getstudentvisaissueddetails(StudentCertificatesPOJO pojo) {
		return service.getstudentvisaissueddetails(pojo);
	}

	public ArrayList<StudentCertificatesVo> displayageissueddetails(String stuid, String agecetiid, String selection) {
		return service.displayageissueddetails(stuid,agecetiid,selection);
	}

	public ArrayList<StudentCertificatesVo> displayconductdetails(String stuid, String agecetiid, String selection) {
		return service.displayconductdetails(stuid,agecetiid,selection);
	}

	public ArrayList<StudentCertificatesVo> displaystudentvisadetails(String stuid, String agecetiid) {
		return service.displaystudentvisadetails(stuid,agecetiid);
	}

	public ArrayList<StudentRegistrationVo> getDetalofStu(String accyear,
			String stuname, String admissionno) {
		return null;
		//return service.getDetalofStu(accyear,stuname,admissionno);
		}

}
