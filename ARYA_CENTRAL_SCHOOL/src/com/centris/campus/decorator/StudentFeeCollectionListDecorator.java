package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.StudentRegistrationVo;

public class StudentFeeCollectionListDecorator extends TableDecorator {

	String check;

	public String getSno() {

		String chkbx = "";

		StudentRegistrationVo detils = (StudentRegistrationVo) getCurrentRowObject();
		
		System.out.println("student name  ::: "+detils.getStudentId() +" ::: "+detils.getStudentnamelabel());
		
		chkbx = "<span class=\"sno\"  id=\""
				+ detils.getStudentId() +","+detils.getClassDetailId()+","+detils.getSection_id()
				+ "\" >"+detils.getSno()+"</span>";

		return chkbx;

	}
}
