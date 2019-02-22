package com.centris.campus.decorator;


import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.QuotaMasterVO;

public class QuotaDecorator extends TableDecorator {
	
	
		String quotacheckbox;

		public String getCheck()

		{
			QuotaMasterVO QuotaDetails = (QuotaMasterVO) getCurrentRowObject();
			String desc = QuotaDetails.getQuotadescription();

			if (desc == null) {
				desc = "";
			}

			String quotacheckbox = "<input class=\"quotaDetails_Checkbox_Class\" type=\"checkbox\" id=\"quota_"
					+ QuotaDetails.getQuotaid()
					+ ","
					+ QuotaDetails.getQuotaName() + "," + desc + "\"/>";

			return quotacheckbox;

		}

	}


