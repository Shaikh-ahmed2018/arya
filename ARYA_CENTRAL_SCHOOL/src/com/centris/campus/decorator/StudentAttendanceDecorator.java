package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;
import com.centris.campus.vo.StudentAttendanceVo;

public class StudentAttendanceDecorator extends TableDecorator{
	
	String check;

	public String getCheck() {
		
		String chkbx = "";
		
		StudentAttendanceVo obj = (StudentAttendanceVo) getCurrentRowObject();
		
		chkbx = "<input type=\"checkbox\" class=\"attendance_Checkbox_Class\" id=\""
				+obj.getStudentid()+ "," + obj.getMonth_int()+","+obj.getYear() + "\" />";
		
		return chkbx;
	}
	
	
	public String getPeriod1() {
		StudentAttendanceVo obj = (StudentAttendanceVo) getCurrentRowObject();
		
		System.out.println("period id :::"+obj.getPeriod1());

		String result = "<select  class='form-control statusclass' name='period1' id='period1' style='width:120px'>"
					+"<option  value='"+obj.getPeriod1().trim()+"' selected>" + ""+obj.getPeriod1().trim()+"" + "</option>"
					+ "</select>";
		return result;
	}

	

	public String getPeriod2() {
		StudentAttendanceVo obj = (StudentAttendanceVo) getCurrentRowObject();

		String result = "<select  class='form-control statusclass' name='period2' id='period2'>"
					+"<option  value='"+obj.getPeriod2().trim()+"' selected>" + ""+obj.getPeriod2().trim()+"" + "</option>"
					+ "</select>";
		return result;
	}

	public String getPeriod3() {
		StudentAttendanceVo obj = (StudentAttendanceVo) getCurrentRowObject();

		String result = "<select  class='form-control statusclass' name='period3' id='period3' >"
					+"<option  value='"+obj.getPeriod3().trim()+"' selected>" + ""+obj.getPeriod3().trim()+"" + "</option>"
					+ "</select>";
		return result;
	}

		public String getPeriod4() {
		StudentAttendanceVo obj = (StudentAttendanceVo) getCurrentRowObject();

		String result = "<select  class='form-control statusclass' name='period4' id='period4' >"
					+"<option  value='"+obj.getPeriod4().trim()+"' selected>" + ""+obj.getPeriod4().trim()+"" + "</option>"
					+ "</select>";
		return result;
	}

		public String getPeriod5() {
		StudentAttendanceVo obj = (StudentAttendanceVo) getCurrentRowObject();

		String result = "<select  class='form-control statusclass' name='period5' id='period5'>"
					+"<option  value='"+obj.getPeriod5().trim()+"' selected>" + ""+obj.getPeriod5().trim()+"" + "</option>"
					+ "</select>";
		return result;
	}

		public String getPeriod6() {
		StudentAttendanceVo obj = (StudentAttendanceVo) getCurrentRowObject();

		String result = "<select  class='form-control statusclass' name='period6' id='period6'>"
					+"<option  value='"+obj.getPeriod6().trim()+"' selected>" + ""+obj.getPeriod6().trim()+"" + "</option>"
					+ "</select>";
		return result;
	}

		public String getPeriod7() {
		StudentAttendanceVo obj = (StudentAttendanceVo) getCurrentRowObject();

		String result = "<select  class='form-control statusclass' name='period7' id='period7'>"
					+"<option  value='"+obj.getPeriod7().trim()+"' selected>" + ""+obj.getPeriod7().trim()+"" + "</option>"
					+ "</select>";
		return result;
	}

	public String getPeriod8() {
		StudentAttendanceVo obj = (StudentAttendanceVo) getCurrentRowObject();

		String result = "<select  class='form-control statusclass' name='period8' id='period8'>"
					+"<option  value='"+obj.getPeriod8().trim()+"' selected>" + ""+obj.getPeriod8().trim()+"" + "</option>"
					+ "</select>";
		return result;
	}
	
	public String getPeriod9() {
		StudentAttendanceVo obj = (StudentAttendanceVo) getCurrentRowObject();

		String result = "<select  class='form-control statusclass' name='period9' id='period9'>"
					+"<option  value='"+obj.getPeriod9().trim()+"' selected>" + ""+obj.getPeriod9().trim()+"" + "</option>"
					+ "</select>";
		return result;
	}
	
	
	

}
