package com.centris.campus.decorator;

import org.displaytag.decorator.TableDecorator;

import com.centris.campus.vo.ExaminationDetailsVo;


public class ExamAccYearDecorator extends TableDecorator {
	public String getStatus() {

		String classspan="";
		ExaminationDetailsVo className = (ExaminationDetailsVo) getCurrentRowObject();
		if(className.getStatus().equalsIgnoreCase("Set")){
		 classspan = "<span  class=\"class_name\" style=\"background-color:rgba(0, 158, 0, 0.66);min-width:80px;display:inline-block;text-align:center; color:#fff;\" > "+className.getStatus()+"</span>";
		}
		else{
			classspan = "<span  class=\"class_name\" style=\"background-color:#FF0000;color:#fff;min-width:80px;display: inline-block;text-align:center;\" >"+className.getStatus()+"</span>";
		}
		return classspan;
	}
	public String getAccYear() {

		
		String classspan="";
		ExaminationDetailsVo exam = (ExaminationDetailsVo) getCurrentRowObject();
		
		 classspan = "<span  class=\"class_name\" style=\"background-color:rgba(0, 158, 0, 0.66);min-width:500px;display:inline-block;text-align:center; color:#fff;\" > "+exam.getAccyear()+"</span>";
		
	
		return classspan;

	}
	
	public String getStartDate() {

		
		String classspan="";
		ExaminationDetailsVo exam = (ExaminationDetailsVo) getCurrentRowObject();
		
		 classspan = "<span  class=\"class_name\" style=\"background-color:rgba(0, 158, 0, 0.66);min-width:80px;display:inline-block;text-align:center; color:#fff;\" > "+exam.getStartDate()+"</span>";
		
	
		return classspan;

	}
	
public String getEndDate() {

		
		String classspan="";
		ExaminationDetailsVo exam = (ExaminationDetailsVo) getCurrentRowObject();
		
		 classspan = "<span  class=\"class_name\" style=\"background-color:rgba(0, 158, 0, 0.66);min-width:80px;display:inline-block;text-align:center; color:#fff;\" > "+exam.getEndDate()+"</span>";
		
	
		return classspan;

	}
	
}
