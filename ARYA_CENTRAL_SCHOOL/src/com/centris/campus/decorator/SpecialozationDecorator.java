package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.SpecializationVo;

public class SpecialozationDecorator  extends TableDecorator{


	public String getSpecCheckBox() {

		SpecializationVo specVo = (SpecializationVo) getCurrentRowObject();

		String specCheckBox = "<input class=\"spec_Checkbox_Class\" type=\"checkbox\" id=\"specCheckBox_"
				+ specVo.getSpec_Id()
				+ ","
				+ specVo.getSpec_Name()
				+ ","
				+ specVo.getStream_Id() + "\"/>";
System.out.println(specVo.getSpec_Id());
		return specCheckBox;

	}



}
