package com.centris.campus.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.daoImpl.AddDesignationDaoImpl;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.delegate.AddDesignationBD;
import com.centris.campus.forms.AddDesignation;
import com.centris.campus.pojo.AddDesignationPojo;
import com.centris.campus.service.AddDesignationService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddDesignationVO;
import com.itextpdf.text.log.SysoLogger;



public class AddDesignationServiceImpl  implements AddDesignationService{

	private static 	Logger logger = Logger.getLogger(AddDesignationServiceImpl.class);
	
	public String insertDesignationDetails(AddDesignation aform)
	
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationServiceImpl:insertDesignationDetails: Starting");
		
		AddDesignationDaoImpl dao=new AddDesignationDaoImpl();
		
		String check="";
		
		
		if(aform.getDesignationid().equalsIgnoreCase(""))
		
		{
		
		
		IDGenerator id=new IDGenerator();
		
		AddDesignationPojo apojo=new AddDesignationPojo();
		
		String s1=null;
		try {
			s1 = id.getPrimaryKeyID("campus_designation");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		apojo.setDesgname(aform.getDesignation_name());
		
		apojo.setDesgdes(aform.getDesignation_description());
		
		apojo.setDesgid(aform.getDesignationid());
		
		apojo.setDesignationcode(s1);
		
		
		

		apojo.setCreateuser(aform.getCreatedby());
		
		
		
		check= dao.insertDesignationDetails(apojo);
		
		}
		
		else if(!aform.getDesignationid().equalsIgnoreCase(""))
			
		{
			
			
			AddDesignationPojo apojo=new AddDesignationPojo();
			
		

			apojo.setDesgname(aform.getDesignation_name());
			
			apojo.setDesgdes(aform.getDesignation_description());
			
			apojo.setDesgid(aform.getDesignationid());
			
			

			apojo.setCreateuser(aform.getCreatedby());
			
			
			
			check= dao.updateDesignationDetails(apojo);
			
			
		}
		
		
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationServiceImpl:insertDesignationDetails: Ending");
		return check;
		
		
	
		
	
	}
	
	
	public  ArrayList<AddDesignationVO> DesignationDetails(AddDesignationVO vo)
	{
		
		
		return new AddDesignationDaoImpl().DesignationDetails(vo);
	
	}
	
	public AddDesignation EditDesignationDetails(AddDesignation aform)
	{

    return new AddDesignationDaoImpl().EditDesignationDetails(aform);
    		
     }


	
	public String deleteDesignationDetails(String[] designation_code) {
		
		return new AddDesignationDaoImpl().deleteDesignationDetails(designation_code);
	}


	
	public ArrayList<AddDesignationVO> getSearchDetails(String searchTextVal) {
		
		return new AddDesignationDaoImpl().getSearchDetails(searchTextVal);
	}


	public boolean getnamecount(AddDesignationVO vo) {
		
		return new AddDesignationDaoImpl().getnamecount(vo);
	}


	
	public ArrayList<AddDesignationVO> DesignationDetails() {
	
		return null;
	}


	


	
	}
