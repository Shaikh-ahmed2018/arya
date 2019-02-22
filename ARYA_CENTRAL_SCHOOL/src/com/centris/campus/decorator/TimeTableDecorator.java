package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;
import com.centris.campus.vo.TimeTableVo;

public class TimeTableDecorator extends TableDecorator {

	public String getDayid() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();
		
		System.out.println("day id :: "+obj.getDayid().trim());

		String result = "<input type='hidden' maxlength='15' value='"
				+ obj.getDayid().trim() + "' name='dayid' ></input>";
		return result;
	}
	
	
	public String getPeriod1() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();
		
		
		String result = "<div class='wrapper'><select  class='form-control subject' name='period11'>"
					+"<option  value='"+obj.getPeriodId11().trim()+"' selected>" + ""+obj.getPeriod11().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod11'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId11().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period12'>"
					+"<option  value='"+obj.getPeriodId12().trim()+"' selected>" + ""+obj.getPeriod12().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod12'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId12().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period13'>"
					+"<option  value='"+obj.getPeriodId13().trim()+"' selected>" + ""+obj.getPeriod13().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod13'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId13().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period14'>"
					+"<option  value='"+obj.getPeriodId14().trim()+"' selected>" + ""+obj.getPeriod14().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod14'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId14().trim()+"' /></div>";
				
		return result;
	}

	

	public String getPeriod2() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();

		String result = "<div class='wrapper'><select  class='form-control subject' name='period21'>"
					+"<option  value='"+obj.getPeriodId21().trim()+"' selected>" + ""+obj.getPeriod21().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod21'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId21().trim()+"' /></div>"
					+ "<div class='wrapper'><select  class='form-control subject' name='period22'>"
					+"<option  value='"+obj.getPeriodId22().trim()+"' selected>" + ""+obj.getPeriod22().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod22'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId22().trim()+"' /></div>"
					+ "<div class='wrapper'><select  class='form-control subject' name='period23'>"
					+"<option  value='"+obj.getPeriodId23().trim()+"' selected>" + ""+obj.getPeriod23().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod23'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId23().trim()+"' /></div>"
					+ "<div class='wrapper'><select  class='form-control subject' name='period24'>"
					+"<option  value='"+obj.getPeriodId24().trim()+"' selected>" + ""+obj.getPeriod24().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod24'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId24().trim()+"' /></div>";
				
		return result;
	}

	public String getPeriod3() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();

		String result = "<div class='wrapper'><select  class='form-control subject' name='period31' >"
					+"<option  value='"+obj.getPeriodId31().trim()+"' selected>" + ""+obj.getPeriod31().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod31'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId31().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period32' >"
					+"<option  value='"+obj.getPeriodId32().trim()+"' selected>" + ""+obj.getPeriod32().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod32'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId32().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period33' >"
					+"<option  value='"+obj.getPeriodId33().trim()+"' selected>" + ""+obj.getPeriod33().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod33'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId33().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period34' >"
					+"<option  value='"+obj.getPeriodId34().trim()+"' selected>" + ""+obj.getPeriod34().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod34'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId34().trim()+"' /></div>";
		return result;
	}

		public String getPeriod4() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();

		String result = "<div class='wrapper'><select  class='form-control subject' name='period41' >"
					+"<option  value='"+obj.getPeriodId41().trim()+"' selected>" + ""+obj.getPeriod41().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod41'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId41().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period42' >"
					+"<option  value='"+obj.getPeriodId42().trim()+"' selected>" + ""+obj.getPeriod42().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod42'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId42().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period43' >"
					+"<option  value='"+obj.getPeriodId43().trim()+"' selected>" + ""+obj.getPeriod43().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod43'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId43().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period44' >"
					+"<option  value='"+obj.getPeriodId44().trim()+"' selected>" + ""+obj.getPeriod44().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod44'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId44().trim()+"' /></div>";
		return result;
	}

		public String getPeriod5() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();

		String result = "<div class='wrapper'><select  class='form-control subject' name='period51' >"
					+"<option  value='"+obj.getPeriodId51().trim()+"' selected>" + ""+obj.getPeriod51().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod51'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId51().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period52' >"
					+"<option  value='"+obj.getPeriodId52().trim()+"' selected>" + ""+obj.getPeriod52().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod52'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId52().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period53' >"
					+"<option  value='"+obj.getPeriodId53().trim()+"' selected>" + ""+obj.getPeriod53().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod53'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId53().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period54' >"
					+"<option  value='"+obj.getPeriodId54().trim()+"' selected>" + ""+obj.getPeriod54().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod54'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId54().trim()+"' /></div>";
		return result;
	}

		public String getPeriod6() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();

		String result = "<div class='wrapper'><select  class='form-control subject' name='period61' >"
					+"<option  value='"+obj.getPeriodId61().trim()+"' selected>" + ""+obj.getPeriod61().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod61'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId61().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period62' >"
					+"<option  value='"+obj.getPeriodId62().trim()+"' selected>" + ""+obj.getPeriod62().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod62'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId62().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period63' >"
					+"<option  value='"+obj.getPeriodId63().trim()+"' selected>" + ""+obj.getPeriod63().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod63'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId63().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period64' >"
					+"<option  value='"+obj.getPeriodId64().trim()+"' selected>" + ""+obj.getPeriod64().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod64'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId64().trim()+"' /></div>";
		return result;
	}

		public String getPeriod7() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();

		String result = "<div class='wrapper'><select  class='form-control subject' name='period71'>"
					+"<option  value='"+obj.getPeriodId71().trim()+"' selected>" + ""+obj.getPeriod71().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod71'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId71().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period72'>"
					+"<option  value='"+obj.getPeriodId72().trim()+"' selected>" + ""+obj.getPeriod72().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod72'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId72().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period73'>"
					+"<option  value='"+obj.getPeriodId73().trim()+"' selected>" + ""+obj.getPeriod73().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod73'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId73().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period74'>"
					+"<option  value='"+obj.getPeriodId74().trim()+"' selected>" + ""+obj.getPeriod74().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod74'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId74().trim()+"' /></div>";
		return result;
	}

	public String getPeriod8() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();

		String result = "<div class='wrapper'><select  class='form-control subject' name='period81' >"
					+"<option  value='"+obj.getPeriodId81().trim()+"' selected>" + ""+obj.getPeriod81().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod81'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId81().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period82' >"
					+"<option  value='"+obj.getPeriodId82().trim()+"' selected>" + ""+obj.getPeriod82().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod82'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId82().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period83' >"
					+"<option  value='"+obj.getPeriodId83().trim()+"' selected>" + ""+obj.getPeriod83().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod83'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId83().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period84' >"
					+"<option  value='"+obj.getPeriodId84().trim()+"' selected>" + ""+obj.getPeriod84().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod84'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId84().trim()+"' /></div>";
		return result;
	}


	public String getPeriod9() {
		TimeTableVo obj = (TimeTableVo) getCurrentRowObject();

		String result = "<div class='wrapper'><select  class='form-control subject' name='period91' >"
					+"<option  value='"+obj.getPeriodId91().trim()+"' selected>" + ""+obj.getPeriod91().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod91'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId91().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period92' >"
					+"<option  value='"+obj.getPeriodId92().trim()+"' selected>" + ""+obj.getPeriod92().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod92'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId92().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period93' >"
					+"<option  value='"+obj.getPeriodId93().trim()+"' selected>" + ""+obj.getPeriod93().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod93'>"
					+ "</select>"
					+ "<input type='hidden' class='tperiod'  value='"+obj.getTeacherId93().trim()+"' /></div>"
					+"<div class='wrapper'><select  class='form-control subject' name='period94' >"
					+"<option  value='"+obj.getPeriodId94().trim()+"' selected>" + ""+obj.getPeriod94().trim()+"" + "</option>"
					+ "</select>"
					+ "<select  class='form-control teacher' name='tperiod94'>"
					+ "</select>"
					+"<input type='hidden' class='tperiod'  value='"+obj.getTeacherId94().trim()+"' /></div>";
		return result;
	}

}
