package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.StudentRegistrationVo;

public class StudentRegistrationDecorator extends TableDecorator {

	String check;

	public String getCheck() {

		String chkbx = "";

		StudentRegistrationVo detils = (StudentRegistrationVo) getCurrentRowObject();
		
		System.out.println("student name  ::: "+detils.getStudentId() +" ::: "+detils.getStudentnamelabel());
		
		chkbx = "<input type=\"checkbox\" class=\"Checkbox_Class\"  id=\"CheckBox_"
				+ detils.getStudentId()
				+ ","
				+ detils.getStudentnamelabel()
				+ "," + "\" />";

		return chkbx;

	}
}
