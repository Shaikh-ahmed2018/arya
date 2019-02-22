package com.centris.campus.serviceImpl;

import java.util.ArrayList;

import com.centris.campus.dao.StudentCertificatesDao;
import com.centris.campus.daoImpl.StudentCertificatesDaoImpl;
import com.centris.campus.pojo.StudentCertificatesPOJO;
import com.centris.campus.service.StudentCertificatesService;
import com.centris.campus.vo.StudentCertificatesVo;

public class StudentCertificatesServiceImpl implements StudentCertificatesService{

	static StudentCertificatesDao dao;
	
	static{
		dao = new StudentCertificatesDaoImpl();
	}
	@Override
	public ArrayList<StudentCertificatesVo> getStudentsDetails(String loc,String acyear) {
		return dao.getStudentsDetails(loc,acyear);
	}
	@Override
	public StudentCertificatesVo getStudentCertificate(StudentCertificatesPOJO pojo) {
		return dao.getStudentCertificate(pojo);
	}
	@Override
	public String saveAgeCertificateData(StudentCertificatesPOJO pojo) {
		return dao.saveAgeCertificateData(pojo);
	}
	@Override
	public ArrayList<StudentCertificatesVo> getissueddetails(StudentCertificatesPOJO pojo) {
		return dao.getissueddetails(pojo);
	}
	@Override
	public String saveBonafiedCertificateData(StudentCertificatesPOJO pojo) {
		return dao.saveBonafiedCertificateData(pojo);
	}
	@Override
	public ArrayList<StudentCertificatesVo> getbonafiedissueddetails(StudentCertificatesPOJO pojo) {
		return dao.getbonafiedissueddetails(pojo);
	}
	@Override
	public String saveCounductedCertificateData(StudentCertificatesPOJO pojo) {
		return dao.saveCounductedCertificateData(pojo);
	}
	@Override
	public ArrayList<StudentCertificatesVo> getconductissueddetails(StudentCertificatesPOJO pojo) {
		return dao.getconductissueddetails(pojo);
	}
	@Override
	public String saveCourseCounductedCertificateData(StudentCertificatesPOJO pojo) {
		return dao.saveCourseCounductedCertificateData(pojo);
	}
	@Override
	public ArrayList<StudentCertificatesVo> getcourseconductissueddetails(StudentCertificatesPOJO pojo) {
		return dao.getcourseconductissueddetails(pojo);
	}
	@Override
	public String saveStudentVisaCertificateData(StudentCertificatesPOJO pojo) {
		return dao.saveStudentVisaCertificateData(pojo);
	}
	@Override
	public ArrayList<StudentCertificatesVo> getstudentvisaissueddetails(StudentCertificatesPOJO pojo) {
		return dao.getstudentvisaissueddetails(pojo);
	}
	@Override
	public ArrayList<StudentCertificatesVo> displayageissueddetails(String stuid, String agecetiid,String selection) {
		return dao.displayageissueddetails(stuid,agecetiid,selection);
	}
	@Override
	public ArrayList<StudentCertificatesVo> displayconductdetails(String stuid, String agecetiid, String selection) {
		return dao.displayconductdetails(stuid,agecetiid,selection);
	}
	@Override
	public ArrayList<StudentCertificatesVo> displaystudentvisadetails(String stuid, String agecetiid) {
		return dao.displaystudentvisadetails(stuid,agecetiid);
	}
	
	
	
	
}
