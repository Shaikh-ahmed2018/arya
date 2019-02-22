package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.StageFeeSetupVo;

public class StageFeeSetupDecorator extends TableDecorator{
	int i = 0;



	public String getFeeSetupDetailsCheckBox() {

		StageFeeSetupVo feeSetupVo = (StageFeeSetupVo) getCurrentRowObject();

		String streamDetailsCheckBox = "<input class=\"feeSetupDetails_Checkbox_Class\" type=\"checkbox\" id=\"feeSetupDetailsCheckBox_"
				+ feeSetupVo.getFeecode() + "\"/>";

		return streamDetailsCheckBox;
	}

	public String getFeeamount() {

		StageFeeSetupVo detailsVO = (StageFeeSetupVo) getCurrentRowObject();

		String feeVal = detailsVO.getFeeamount();
		String feeamount = "<input  type=\"text\" name=\"feeamount\"  value=\""
				+ feeVal + "\" class=\"feeamount\" id=\""
				+ detailsVO.getFeecode() + "," + detailsVO.getFeename() + ","
				+ detailsVO.getFeesettingcode() + "\"/>";

		return feeamount;
	}

	public String getDelete() {

		StageFeeSetupVo detailsVO = (StageFeeSetupVo) getCurrentRowObject();

		String deletefee = null;
		
		
		deletefee="<span class=\"glyphicon glyphicon-trash2\" onclick='deleteFee(\""
					+ detailsVO.getFeesettingcode()
					+ ","
					+ detailsVO.getFeecode()
					+ ","
					+ detailsVO.getAccyearid()
					+ ","
					+ detailsVO.getClassid() 
					+ ","
					+ detailsVO.getTermid()+ "\")' ";
		
		return deletefee;

	}
	

public String getFeecode() {
	StageFeeSetupVo detailsVO = (StageFeeSetupVo) getCurrentRowObject();
	
	String feeCode=detailsVO.getFeecode();
	
	String feecode="<span name=\"feecode\">"+feeCode+"</span>";
	
	return feecode;
}




}
