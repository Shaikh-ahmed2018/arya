    package com.centris.campus.decorator;

	import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.ThirdformformatVO;

	public class ThirdadmissionDetailsDecorator extends TableDecorator{
		
		
		public String getTemporary_details_checkbox() {

			ThirdformformatVO detailsVO = (ThirdformformatVO) getCurrentRowObject();

			String streamDetailsCheckBox = "<input class=\"temporary_admission_details_Checkbox_Class\" type=\"checkbox\" id=\"streamDetailsCheckBox_"
					+ detailsVO.getStu_id()
					+ ","
					+ detailsVO.getStu_name()
					+ "\"/>";

			return streamDetailsCheckBox;
		}

		


	}


