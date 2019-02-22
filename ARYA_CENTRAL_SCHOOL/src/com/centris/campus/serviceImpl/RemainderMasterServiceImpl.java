package com.centris.campus.serviceImpl;

import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.centris.campus.actions.AdminMenuAction;
import com.centris.campus.dao.RemainderMasterDAO;
import com.centris.campus.dao.TermMasterDAO;
import com.centris.campus.daoImpl.RemainderMasterDAOIMPL;
import com.centris.campus.daoImpl.TermMasterDAOIMPL;
import com.centris.campus.service.RemainderMasterService;
import com.centris.campus.util.JDate;
import com.centris.campus.util.JLogger;
import com.centris.campus.util.MessageConstants;
import com.centris.campus.vo.RemainderMasterVO;
import com.centris.campus.vo.TermMasterVo;

public class RemainderMasterServiceImpl implements RemainderMasterService 

{
	
	private static final Logger logger = Logger
			.getLogger(AdminMenuAction.class);

	RemainderMasterDAO obj_Remain = new RemainderMasterDAOIMPL();

	
	public boolean getnamecount(RemainderMasterVO vo) 
	
	{
		
		boolean count=false;
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterServiceImpl : getnamecount Starting");
		
		if (vo.getId()==null)
			
		{
			
			count=obj_Remain.getnamecount(vo);
			
		}
		
		else if (!vo.getId().equalsIgnoreCase(""))
			
		{
			
			count=obj_Remain.geteditcount(vo);
			
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterServiceImpl : getnamecount Ending");
		
		return count;
	}


	
	public String addremainderdata(RemainderMasterVO vo) 
	
	{
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterServiceImpl :addremainderdata  Starting");
		
		
		String check="";
		
		if (vo.getId()==null || vo.getId().equalsIgnoreCase(""))
			
		{
		
		try
		
		{
			
			check=obj_Remain.addremainderdata(vo);
			
		}
		
		catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
		
		}
		
		else if (!vo.getId().equalsIgnoreCase(""))
			
		{
			
			
			try
			
			{
				
				check=obj_Remain.updateremainderdata(vo);
				
			}
			
			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
			
		}
		

		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterServiceImpl :addremainderdata Ending");
		return check;
		
	
	
	}



	
	public ArrayList<RemainderMasterVO> remainderdetails(RemainderMasterVO vo) {
		
		logger.setLevel(Level.DEBUG);
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.START_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterServiceImpl :remainderdetails  Starting");
		
		ArrayList<RemainderMasterVO> remainderdetails = new ArrayList<RemainderMasterVO>();
		
		if (vo.getName()==null || vo.getName().equalsIgnoreCase(""))
			
		{
			
			
			try
			
			{
				
				remainderdetails=obj_Remain.remainderdetails(vo);
				
			}
			
			catch (Exception e) {
				logger.error(e.getMessage(), e);
				e.printStackTrace();
			}
			
			
		}
		
		else if (!vo.getName().equalsIgnoreCase(""))
			
		{
			
			remainderdetails=obj_Remain.searchdetails(vo);
			
		}
		
		
		
		
		JLogger.log(0, JDate.getTimeString(new Date())
				+ MessageConstants.END_POINT);
		logger.info(JDate.getTimeString(new Date())
				+ " Control in RemainderMasterServiceImpl :remainderdetails Ending");
		
		return remainderdetails;
	}



	
	public RemainderMasterVO editremainderDetails(RemainderMasterVO vo) {
		
		return obj_Remain.editremainderDetails(vo);
	}



	
	public String deleteRemainderDetails(RemainderMasterVO vo) {
		
		return obj_Remain.deleteRemainderDetails(vo);
	}
	
}
