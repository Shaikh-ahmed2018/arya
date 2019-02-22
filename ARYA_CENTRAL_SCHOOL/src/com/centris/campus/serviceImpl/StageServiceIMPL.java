package com.centris.campus.serviceImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import com.centris.campus.daoImpl.IDGenerator;
import com.centris.campus.daoImpl.StageDAOIMPL;
import com.centris.campus.forms.AddStageForm;
import com.centris.campus.pojo.AddStagePojo;
import com.centris.campus.service.StageService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.AddStageVO;

public class StageServiceIMPL implements StageService

{
	private static 	Logger logger = Logger.getLogger(StageServiceIMPL.class);

	
	
	public ArrayList<AddStageVO> StageDetails(String accyear)
	
	{
		

		return new StageDAOIMPL().StageDetails(accyear);

	}

	public String insertStage(AddStageForm aform) 
	{
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationServiceImpl:insertStage: Starting");
		
		StageDAOIMPL dao=new StageDAOIMPL();
		
		String check="";
		
		
		if(aform.getStageid().equalsIgnoreCase(""))
		
		{
		AddStagePojo apojo=new AddStagePojo();
		
		try {

			String s1=IDGenerator.getPrimaryKeyID("campus_fee_stage");

			apojo.setStage_name(aform.getStage_name());
			apojo.setAmount(aform.getAmount());
			apojo.setStage_description(aform.getStage_description());
			apojo.setStageid(aform.getStageid());
			apojo.setStagecode(s1);
			apojo.setCreateuser(aform.getCreatedby());
			apojo.setAccyear(aform.getAccyear());
			check= dao.insertstage(apojo);
			
		} 
		catch (SQLException e)
		{
			
			logger.error(e);
			e.printStackTrace();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		else if(!aform.getStageid().equalsIgnoreCase(""))
		{
			
			AddStagePojo apojo=new AddStagePojo();
			apojo.setStage_name(aform.getStage_name());
			apojo.setAmount(aform.getAmount());
			apojo.setStage_description(aform.getStage_description());
			apojo.setStageid(aform.getStageid());
			apojo.setCreateuser(aform.getCreatedby());
			apojo.setAccyear(aform.getAccyear());
			check= dao.updatestage(apojo);
			
		}
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in AddDesignationServiceImpl:insertStage: Ending");
		return check;
		
	
	}

	
	public AddStageForm EditStageDetails(AddStageForm aform)
	
	{
	    return new StageDAOIMPL().EditStageDetails(aform);
	}

	public String deleteStage(String[] stageid)
	{
		return new StageDAOIMPL().deleteStage(stageid);
	}

	public boolean getstagecount(AddStageVO vo)
	{
	return new StageDAOIMPL().getstagecount(vo);

	}

	public ArrayList<AddStageVO> SelectAllSatges(AddStageVO vo) {
		return new StageDAOIMPL().SelectAllSatges(vo);
	}

	public List<AddStageVO> searchStage(String searchName, String accyear) {
		return new StageDAOIMPL().searchStage(searchName,accyear);
	}

	@Override
	public ArrayList<AddStageVO> StageDetails(AddStageVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	}


