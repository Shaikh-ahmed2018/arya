package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;


import com.centris.campus.vo.LocationVO;

public class LocationDecorator extends TableDecorator {


	public String getLocationCheckBox() {

		LocationVO locVo = (LocationVO) getCurrentRowObject();

		String locationCheckBox = "<input class=\"loc_Checkbox_Class\" type=\"checkbox\" id=\"locCheckBox_"
				+ locVo.getLocation_id()
				+ ","
				+ locVo.getLocationname()
				+ ","
				+ locVo.getDesc() + "\"/>";

		return locationCheckBox;

	}



}
