/**
 * 
 */
package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.AllTeacherDetailsVo;
import com.centris.campus.vo.ViewallSubjectsVo;

/**
 * @author preethi
 * 
 */
public class AllTeacherDetailsDecorator extends TableDecorator {

	String check;

	public String getCheck() {

		String chkbx = "";
		AllTeacherDetailsVo obj = (AllTeacherDetailsVo) getCurrentRowObject();
		chkbx = "<input type=\"checkbox\" class=\"vehicle_Checkbox_Class\" id=\"VehicleCheckBox_"
				+ obj.getTeacherId() + "\" />";

		return chkbx;

	}

}
