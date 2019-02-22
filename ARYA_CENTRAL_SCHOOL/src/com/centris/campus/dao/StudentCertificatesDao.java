package com.centris.campus.dao;

import java.util.ArrayList;

import com.centris.campus.pojo.StudentCertificatesPOJO;
import com.centris.campus.vo.StudentCertificatesVo;

public interface StudentCertificatesDao {

	ArrayList<StudentCertificatesVo> getStudentsDetails(String loc, String acyear);

	StudentCertificatesVo getStudentCertificate(StudentCertificatesPOJO pojo);

	String saveAgeCertificateData(StudentCertificatesPOJO pojo);

	ArrayList<StudentCertificatesVo> getissueddetails(StudentCertificatesPOJO pojo);

	String saveBonafiedCertificateData(StudentCertificatesPOJO pojo);

	ArrayList<StudentCertificatesVo> getbonafiedissueddetails(StudentCertificatesPOJO pojo);

	String saveCounductedCertificateData(StudentCertificatesPOJO pojo);

	ArrayList<StudentCertificatesVo> getconductissueddetails(StudentCertificatesPOJO pojo);

	String saveCourseCounductedCertificateData(StudentCertificatesPOJO pojo);

	ArrayList<StudentCertificatesVo> getcourseconductissueddetails(StudentCertificatesPOJO pojo);

	String saveStudentVisaCertificateData(StudentCertificatesPOJO pojo);

	ArrayList<StudentCertificatesVo> getstudentvisaissueddetails(StudentCertificatesPOJO pojo);

	ArrayList<StudentCertificatesVo> displayageissueddetails(String stuid, String agecetiid, String selection);

	ArrayList<StudentCertificatesVo> displayconductdetails(String stuid, String agecetiid, String selection);

	ArrayList<StudentCertificatesVo> displaystudentvisadetails(String stuid, String agecetiid);

}
