package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.ClassFeeSetupVo;

public class FeeMasterSetupDecorator extends TableDecorator {
	int i = 0;



	public String getFeeSetupDetailsCheckBox() {

		ClassFeeSetupVo feeSetupVo = (ClassFeeSetupVo) getCurrentRowObject();

		String streamDetailsCheckBox = "<input class=\"feeSetupDetails_Checkbox_Class\" type=\"checkbox\" id=\"feeSetupDetailsCheckBox_"
				+ feeSetupVo.getFeecode() + "\"/>";

		return streamDetailsCheckBox;
	}

	public String getFeeamount() {

		ClassFeeSetupVo detailsVO = (ClassFeeSetupVo) getCurrentRowObject();

		String feeVal = detailsVO.getFeeamount();
		String feeamount = "<input  type=\"text\" name=\"feeamount\"  value=\""
				+ feeVal + "\" class=\"feeamount\" id=\""
				+ detailsVO.getFeecode() + "," + detailsVO.getFeename() + ","
				+ detailsVO.getFeesettingcode() + "\"/>";

		return feeamount;
	}

	public String getDelete() {

		ClassFeeSetupVo detailsVO = (ClassFeeSetupVo) getCurrentRowObject();

		String deletefee = null;
		
		/*deletefee="<a href=\"feeSetup.html?method=deleteFees&FeeSettingsCode="+detailsVO.getFeesettingcode()+"&FeeCode="+detailsVO.getFeecode()+"&acadamicYear="+detailsVO.getAcadamicyear()+"&classid="+detailsVO.getClassid()+"&term="+detailsVO.getTerm()+"\">delete</a>";*/

		/*deletefee = "<input type=\"button\" class=\"feeamountdeletefee_Class\" value=\"delete\" id=\" "
				+ detailsVO.getFeesettingcode()
				+ ","
				+ detailsVO.getFeecode()
				+ ","
				+ detailsVO.getAcadamicyear()
				+ ","
				+ detailsVO.getClassid() 
				+ ","
				+ detailsVO.getTerm() + " \"/>";*/
		
		deletefee="<span  class=\"glyphicon glyphicon-trash2\" onclick='deleteFee(\""
					+ detailsVO.getFeesettingcode()
					+ ","
					+ detailsVO.getFeecode()
					+ ","
					+ detailsVO.getAcadamicyear()
					+ ","
					+ detailsVO.getClassid() 
					+ ","
					+ detailsVO.getTerm()
					+ ","
					+detailsVO.getSpecialization_id()+ "\")' ";
		
		return deletefee;

	}
	

public String getFeecode() {
	ClassFeeSetupVo detailsVO = (ClassFeeSetupVo) getCurrentRowObject();
	
	String feeCode=detailsVO.getFeecode();
	
	String feecode="<span name=\"feecode\">"+feeCode+"</span>";
	
	return feecode;
}




}