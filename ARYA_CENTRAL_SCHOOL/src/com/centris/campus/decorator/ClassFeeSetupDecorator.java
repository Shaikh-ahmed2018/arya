package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.ClassFeeSetupVo;

public class ClassFeeSetupDecorator extends TableDecorator {
	public String getStatus() {

		
		String classspan="";
		ClassFeeSetupVo className = (ClassFeeSetupVo) getCurrentRowObject();
		if(className.getFeecount()>0){
		 classspan = "<span  class=\"class_name\" style=\"background-color:rgba(0, 158, 0, 0.66);min-width:80px;display:inline-block;text-align:center; color:#fff;\" > "+className.getStatus()+"</span>";
		}
		else{
			classspan = "<span  class=\"class_name\" style=\"background-color:#FF0000;color:#fff;min-width:80px;display: inline-block;text-align:center;\" >"+className.getStatus()+"</span>";
		}
		return classspan;

	}


}
