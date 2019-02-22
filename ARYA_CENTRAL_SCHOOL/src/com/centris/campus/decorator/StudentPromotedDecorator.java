package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.StudentRegistrationVo;

public class StudentPromotedDecorator extends TableDecorator {
	
	public String getclassname() {
		String classid = "";
		StudentRegistrationVo detils = (StudentRegistrationVo) getCurrentRowObject();
		classid="<span class=\""+detils.getClasscode()+"\" >"+detils.getClassname()+"</span>";
		return classid;
	}
	
	public String getcount() {
		String studentid = "";
		StudentRegistrationVo detils = (StudentRegistrationVo) getCurrentRowObject();
		studentid="<span class=\""+detils.getStudentId()+" "+detils.getLocationId()+" "+detils.getAcademicYearId()+" "+detils.getPromotionId()+" "+"studentid"+"\" >"+detils.getCount()+"</span>";
		return studentid;
	}
	
	public String getstudentAdmissionNo() {
		String studentadmissionno = "";
		StudentRegistrationVo detils = (StudentRegistrationVo) getCurrentRowObject();
		studentadmissionno="<span class=\""+detils.getStudentAdmissionNo()+"\" >"+detils.getStudentAdmissionNo()+"</span>";
		return studentadmissionno;
	}
	
	public String getstudentrollno() {
		String studentrollno = "";
		StudentRegistrationVo detils = (StudentRegistrationVo) getCurrentRowObject();
		studentrollno="<span class=\""+detils.getStudentrollno()+"\" >"+detils.getStudentrollno()+"</span>";
		return studentrollno;
	}
	
	public String getsectionnaem() {
		String sectionid = "";
		StudentRegistrationVo detils = (StudentRegistrationVo) getCurrentRowObject();
		sectionid="<span class=\""+detils.getSectioncode()+"\" >"+detils.getSectionnaem()+"</span>";
		return sectionid;
	}
	
	public String getspecilizationname() {
		String specilizationid = "";
		StudentRegistrationVo detils = (StudentRegistrationVo) getCurrentRowObject();
		specilizationid="<span class=\""+detils.getSpecilization()+"\" >"+detils.getSpecilizationname()+"</span>";
		return specilizationid;
	}
	
}
